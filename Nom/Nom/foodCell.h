//
//  foodCell.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface foodCell : UITableViewCell
@property (nonatomic, strong) UILabel *label;
@property (nonatomic, strong) UIImage *pic;

-(foodCell *)updatePic:(NSString *)update;
// -(id)initWithName:(NSString *)name andPic:(UIImage *)pic;

@end
