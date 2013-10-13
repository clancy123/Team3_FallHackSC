//
//  hallCell.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "hallCell.h"

@implementation hallCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
      self.backgroundColor = [UIColor redColor];
      self.label = [[UILabel alloc] init];
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
  self.backgroundColor = [UIColor colorWithRed:.85 green:.7 blue:.1 alpha:.75];
  self.label = [[UILabel alloc] initWithFrame:CGRectMake(16, 4, 200, 50)];
  self.label.text = @"LOCATION";
  self.label.font =  [UIFont fontWithName:@"Helvetica Neue" size:20];

  self.label.textColor = [UIColor colorWithRed:1 green:.1 blue:.1 alpha:1];
  [self.contentView addSubview:self.label];
  return self;
}

@end
