//
//  Meal.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Meal : NSObject
{
  /*
  NSString *title;
  BOOL *vegan;
  BOOL *vegetarian;
  UIImage *foodPic;
   */
}
@property (nonatomic,strong) NSString *title;
@property (nonatomic) BOOL *vegan;
@property (nonatomic) BOOL *vegetarian;
@property (nonatomic,strong) NSString *foodPic;

+(id)mealWithTitle:(NSString *)name andImage:(NSString *)pic;

@end
