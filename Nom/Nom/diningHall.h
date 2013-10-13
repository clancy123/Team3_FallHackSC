//
//  diningHall.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
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
}mealType;

@interface diningHall : NSObject <NSURLConnectionDataDelegate> {
  NSMutableArray *meals;
  NSURLConnection * connection;
  NSMutableData * downData;
}

@property (nonatomic, copy) NSString *diningHallName;
@property (nonatomic, copy) UIImage  *image;
@property (nonatomic,strong) NSURL * menuPath;

+ (id)itemWithTitle:(NSString *)title withImage:(UIImage *)image;
- (id)initWithTitle:(NSString *)title withImage:(UIImage *)image;
- (id)initWithDiningHall:(DiningOption)hall;

// -(void)setUpMeals;
-(void)addMeal:(NSString *)mealName withImage:(NSString *)image;
-(NSMutableArray *)getMeals;
-(void)loadData;
+(NSString*)getPathForHall:(DiningOption) hall onMeal:(mealType)type;
+(NSString*)getPathForHall:(DiningOption) hall onMeal:(mealType)type forWeek:(int)week;


@end