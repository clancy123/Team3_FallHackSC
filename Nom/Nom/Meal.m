//
//  Meal.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "Meal.h"

@implementation Meal

+(id)mealWithTitle:(NSString *)name andImage:(NSString *)pic{
  return [[self alloc] initWithTitle:name andImage:pic];
}

-(id)initWithTitle:(NSString *)foodName andImage:(NSString *)pic {
  if ((self = [super init])) {
    self.title = foodName;
    self.foodPic = pic;
  }
  return self;
}
/*
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
 [self setUpMeals];
 }
 
 return self;
 }
 */

@end
