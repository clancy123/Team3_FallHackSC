//
//  CenterViewController.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#include "diningHall.h"

#import <UIKit/UIKit.h>
#import "LeftViewController.h"
#include "Meal.h"

@protocol CenterViewControllerDelegate <NSObject>

@optional
- (void)movePanelLeft;
- (void)movePanelRight;
- (void)imageSelected:(UIImage *)image
            withTitle:(NSString *)imageTitle;
- (void)mealSelected:(Meal *)meal;


@required
- (void)movePanelToOriginalPosition;

@end

@interface CenterViewController : UIViewController <LeftViewControllerDelegate, UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, assign) id<CenterViewControllerDelegate> delegate;
@property (weak, nonatomic) UIButton *leftButton;
@property (strong, nonatomic) diningHall *selectedHall;
@property NSInteger pageIndex;


- (void)updateTextViews:(BOOL)force;

//-(void)setFoodArrayFromHall;
-(void)btnMovePanelRight;
// left button
@end
