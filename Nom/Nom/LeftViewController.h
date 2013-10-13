//
//  LeftViewController.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "diningHall.h"

@protocol  LeftViewControllerDelegate <NSObject>

@optional
- (void)imageSelected:(UIImage *)image
            withTitle:(NSString *)imageTitle;

@optional
- (void)diningSelected:(diningHall *)dining;

@end
@interface LeftViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, assign) id<LeftViewControllerDelegate> delegate;
@property(nonatomic, strong) diningHall *selected;
@end
