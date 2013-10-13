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

char* HALLS[3] = {"EVK","ParkSide","Cafe84"};
char* MEALS[3] = {"Breakfast%20Lunch","Breakfast%20Lunch","Dinner"};


+ (id)itemWithTitle:(NSString *)title withImage:(UIImage *)image
{
  return [[self alloc] initWithTitle:(NSString *)title withImage:(UIImage *)image];
}

- (id)initWithTitle:(NSString *)title withImage:(UIImage *)image
{
  if ((self = [super init]))
  {
    self.diningHallName = title;
    self.image = image;
    meals = [[NSMutableArray alloc] init];
    //[self setUpMeals];
  }
  
  return self;
}


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

+(NSString*)getPathForHall:(DiningOption) hall onMeal:(mealType)type{
  NSCalendar * weeker = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
  NSDateComponents * cmp = [weeker components: NSWeekOfYearCalendarUnit fromDate:[NSDate date]];
  return [diningHall getPathForHall:hall onMeal:type forWeek:(cmp.weekOfYear-2)];
  //the minus two fixes error... Might need to change week start offset for weeker...
}

+(NSString*)getPathForHall:(DiningOption) hall onMeal:(mealType)type forWeek:(int)week{
  return [NSString stringWithFormat:@"http://hospitality.usc.edu/ResidentialDining/Menu/%s%%20%s%%20Week%%20%i.pdf",HALLS[hall],MEALS[type],week];
}

@end
