//
//  diningHall.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "diningHall.h"
#import "Meal.h"

@implementation diningHall

+ (id)itemWithTitle:(NSString *)title withImage:(UIImage *)image
{
  return [[self alloc] initWithTitle:(NSString *)title withImage:(UIImage *)image];
}

- (id)initWithTitle:(NSString *)title withImage:(UIImage *)image
{
  if ((self = [super init]))
  {
    _title = title;
    _image = image;
    meals = [[NSMutableArray alloc] init];
    //[self setUpMeals];
  }
  
  return self;
}

/*
 - (void)setupHallsArray
 {
 NSArray *halls = @[
 [diningHall itemWithTitle:@"EVK" withImage:[UIImage imageNamed:@"ID-100113060.jpg"]],
 [diningHall itemWithTitle:@"Cafe84" withImage:[UIImage imageNamed:@"ID-10022760.jpg"]],
 [diningHall itemWithTitle:@"IRC" withImage:[UIImage imageNamed:@"testnothing.jpg"]]
 ];
 
 arrayOfDiningHalls = [NSMutableArray arrayWithArray:halls];
 
 [self.myTableView reloadData];
 }
 */
-(void)addMeal:(NSString *)mealName withImage:(NSString *)image {
  [meals addObject:[Meal mealWithTitle:mealName andImage:image]];
}
/*
-(void)setUpMeals {
  NSArray *mealArray = @[
                         [Meal mealWithTitle:@"Cheespider"],
                         [Meal mealWithTitle:@"Tacodile Surpreme"],
                         [Meal mealWithTitle:@"Mosquitoast"]
                         ];
  meals = [NSMutableArray arrayWithArray:mealArray];
}
 */

-(NSMutableArray *)getMeals {
  return meals;
}

@end
