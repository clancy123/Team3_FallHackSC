//
//  diningHall.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface diningHall : NSObject {
  NSMutableArray *meals;
}

+ (id)itemWithTitle:(NSString *)title withImage:(UIImage *)image;
- (id)initWithTitle:(NSString *)title withImage:(UIImage *)image;
// -(void)setUpMeals;
-(void)addMeal:(NSString *)mealName withImage:(NSString *)image;
-(NSMutableArray *)getMeals;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, copy) UIImage  *image;

@end
