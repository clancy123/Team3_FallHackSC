//
//  foodCell.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "foodCell.h"

@implementation foodCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}


- (id)init {
  self = [super init];
  self.backgroundColor = [UIColor redColor];
  self.label = [[UILabel alloc] initWithFrame:CGRectMake(54, 6, 200, 50)];
  self.label.text = @"food";
  self.label.textColor = [UIColor blackColor];
  [self.contentView addSubview:self.label];
 
  self.pic = [UIImage imageNamed:@"cake.jpg"];
  UIImageView *imageView = [[UIImageView alloc] initWithImage:self.pic];
  imageView.frame = CGRectMake(10, 10, 36, 36);
  
  // UIView *photoView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 30, 30)];
  // photoView.backgroundColor = [UIImage imageNamed:@"cake.jpg"];
  [self.contentView addSubview:imageView];
  return self;
}

-(foodCell *)updatePic:(NSString *)update {
  self.pic = [UIImage imageNamed:update];
  UIImageView *imageView = [[UIImageView alloc] initWithImage:self.pic];
  imageView.frame = CGRectMake(10, 10, 36, 36);
  [self.contentView addSubview:imageView];
  return self;
}

-(id)initWithName:(NSString *)name andPic:(UIImage *)pic {
  self = [super init];
  
  self.backgroundColor = [UIColor redColor];
  self.label = [[UILabel alloc] initWithFrame:CGRectMake(54, 6, 200, 50)];
  self.label.text = name;
  self.label.textColor = [UIColor blackColor];
  [self.contentView addSubview:self.label];
  self.pic = pic;
  UIImageView *imageView = [[UIImageView alloc] initWithImage:self.pic];
  imageView.frame = CGRectMake(10, 10, 36, 36);
  [self.contentView addSubview:imageView];
  
  /*
   self.pic = [UIImage imageNamed:@"cake.jpg"];
   UIImageView *imageView = [[UIImageView alloc] initWithImage:self.pic];
   imageView.frame = CGRectMake(10, 10, 36, 36);
   
   // UIView *photoView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 30, 30)];
   // photoView.backgroundColor = [UIImage imageNamed:@"cake.jpg"];
   [self.contentView addSubview:imageView];
   */
  return self;
}

@end
