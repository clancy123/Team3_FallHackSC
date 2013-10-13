//
//  DiningHall.m
//  dining
//
//  Created by Nolan Miller on 10/12/13.
//  Copyright (c) 2013 Nolan Miller. All rights reserved.
//

#import "DiningHall.h"


@implementation DiningHall

char* HALLS[3] = {"EVK","ParkSide","Cafe84"};
char* MEALS[3] = {"Breakfast%20Lunch","Breakfast%20Lunch","Dinner"};

- (id)initWithDiningHall:(DiningOption)hall{
    self = [super init];
    if (self) {
        if (hall==EVK)
            self.diningHallName = @"EVK";
        else if (hall ==Parkside)
            self.diningHallName = @"Parkside";
        else if (hall == Cafe84)
            self.diningHallName = @"Cafe 84";
        self.menuPath = [NSURL URLWithString:[self.class getPathForHall:hall onMeal:Dinner]];
    }
    return self;
}

-(void)loadData{
    downData = [[NSMutableData alloc] init];
    [downData setData:NULL];
    connection = [[NSURLConnection alloc] initWithRequest:[NSURLRequest requestWithURL:self.menuPath] delegate:self];
}
-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data{
    [downData appendData:data];
}
-(void)connectionDidFinishLoading:(NSURLConnection *)connection{
    
}

+(NSString*)getPathForHall:(DiningOption) hall onMeal:(Meal)meal{
    NSCalendar * weeker = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
    NSDateComponents * cmp = [weeker components: NSWeekOfYearCalendarUnit fromDate:[NSDate date]];
    return [DiningHall getPathForHall:hall onMeal:meal forWeek:(cmp.weekOfYear-2)];
    //the minus two fixes error... Might need to change week start offset for weeker...
}

+(NSString*)getPathForHall:(DiningOption) hall onMeal:(Meal)meal forWeek:(int)week{
    return [NSString stringWithFormat:@"http://hospitality.usc.edu/ResidentialDining/Menu/%s%%20%s%%20Week%%20%i.pdf",HALLS[hall],MEALS[meal],week];
}

@end
