//
//  DiningHall.h
//  dining
//
//  Created by Nolan Miller on 10/12/13.
//  Copyright (c) 2013 Nolan Miller. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum{
    EVK,
    Parkside,
    Cafe84
} DiningOption;

typedef enum{
    Breakfast,
    Lunch,
    Dinner
}Meal;





@interface DiningHall : NSObject <NSURLConnectionDataDelegate>{
    NSURLConnection * connection;
    NSMutableData * downData;
}

@property (nonatomic,strong) NSString * diningHallName;
@property (nonatomic,strong) NSURL * menuPath;

- (id)initWithDiningHall:(DiningOption)hall;
-(void)loadData;

+(NSString*)getPathForHall:(DiningOption) hall onMeal:(Meal)meal;
+(NSString*)getPathForHall:(DiningOption) hall onMeal:(Meal)meal forWeek:(int)week;


@end
