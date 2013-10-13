//
//  MainViewController.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <UIKit/UIKit.h>
#include "CenterViewController.h"
@interface MainViewController : UIViewController
{
  UIScrollView *scrollView;
  UIPageControl *pageControl;
  CenterViewController *currentPage;
  CenterViewController *today;
  CenterViewController *nextPage;
  NSMutableArray *allPages;
  
}
-(void)changePage;

@end
