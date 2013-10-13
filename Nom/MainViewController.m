//
//  MainViewController.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#include "MainViewController.h"
#include "LeftViewController.h"
#import <QuartzCore/QuartzCore.h>

#define CENTER_TAG 1
#define LEFT_PANEL_TAG 2
#define CORNER_RADIUS 4
#define SLIDE_TIMING .25
#define PANEL_WIDTH 60

@interface MainViewController () <CenterViewControllerDelegate>
@property (nonatomic, strong) CenterViewController *centerVC;
@property (nonatomic, strong) LeftViewController *leftVC;
@property (nonatomic, assign) BOOL showingLeftPanel;

@end

@implementation MainViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self setupView];
  
  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(setSelectedHall)
                                               name:@"Reload"
                                            object:nil];
  
	// Do any additional setup after loading the view.
}
   
-(void)setSelectedHall {
  currentPage.selectedHall = self.leftVC.selected;
  nextPage.selectedHall = self.leftVC.selected;
  [[NSNotificationCenter defaultCenter] postNotificationName:@"centerVCReload"
                                                      object:self];
  
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (void)setupView
{
  scrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(0,0,self.view.frame.size.width, self.view.frame.size.height)];
  //scrollView.backgroundColor = [UIColor purpleColor];
  scrollView.showsHorizontalScrollIndicator = YES;
  currentPage = [[CenterViewController alloc] init];
  nextPage = [[CenterViewController alloc] init];
  currentPage.view.tag = CENTER_TAG;
  nextPage.view.tag = CENTER_TAG;
  currentPage.delegate = self;
  nextPage.delegate = self;
  
  [scrollView addSubview:currentPage.view];
  [scrollView addSubview:nextPage.view];
  
  NSInteger widthCount = 2;
  scrollView.contentSize =
  CGSizeMake(
             scrollView.frame.size.width * widthCount,
             scrollView.frame.size.height);
	scrollView.contentOffset = CGPointMake(0, 0);
  
	pageControl.numberOfPages = 2;
	pageControl.currentPage = 0;
	
	[self applyNewIndex:0 pageController:currentPage];
	[self applyNewIndex:1 pageController:nextPage];
  
  // [self.view addSubview:currentPage.view];
  [self.view addSubview:scrollView];
  
  /*
  
  self.centerVC = [[CenterViewController alloc] init];
  self.centerVC.view.tag = CENTER_TAG;
  self.centerVC.delegate = self;
  
  [self.view addSubview:self.centerVC.view];
  [self addChildViewController:_centerVC];
   */
}

- (void)showCenterViewWithShadow:(BOOL)value withOffset:(double)offset
{
  if (value) {
    [_centerVC.view.layer setCornerRadius:CORNER_RADIUS];
    [_centerVC.view.layer setShadowColor:[UIColor blackColor].CGColor];
    [_centerVC.view.layer setShadowOpacity:0.8];
    [_centerVC.view.layer setShadowOffset:CGSizeMake(offset, offset)];
  }
  else {
    [_centerVC.view.layer setCornerRadius:0.0f];
    [_centerVC.view.layer setShadowOffset:CGSizeMake(offset, offset)];
  }
}

- (void)resetMainView {
  if (_leftVC !=nil) {
    [self.leftVC.view removeFromSuperview];
    self.leftVC = nil;
    
    _centerVC.leftButton.tag = 0;
    self.showingLeftPanel = NO;
  }
  
  [self showCenterViewWithShadow:NO withOffset:0];
}

- (UIView *)getLeftView
{
  NSLog(@"@@@@@@ ");
  if (_leftVC == nil) {
    NSLog(@"********* NO LEFT. CREATING ******");
    self.leftVC = [[LeftViewController alloc] init];
    self.leftVC.view.tag = LEFT_PANEL_TAG;
    self.leftVC.delegate = currentPage;
    [self.view addSubview:self.leftVC.view];
    [self addChildViewController:_leftVC];
    [_leftVC didMoveToParentViewController:self];
    _leftVC.view.frame = CGRectMake(0,0, self.view.frame.size.width, self.view.frame.size.height);
  }
  else
    NSLog(@" &&& ALready showing ");
  
  self.showingLeftPanel = YES;
  
  [self showCenterViewWithShadow:YES withOffset:-2];
  
  UIView *view = self.leftVC.view;
  return view;
}

- (UIView *)getRightView
{
  UIView *view = nil;
  return view;
}

#pragma mark -
#pragma mark Swipe Gesture Setup/Actions

#pragma mark - setup

- (void)setupGestures
{
}

-(void)movePanel:(id)sender
{
}

#pragma mark -
#pragma mark Delegate Actions

- (void)movePanelLeft // to show right panel
{
}

- (void)movePanelRight // to show left panel
{
  NSLog(@" ### Move to the right ### ");
  UIView *childView = [self getLeftView];
  [self.view sendSubviewToBack:childView];
  
  [UIView animateWithDuration:SLIDE_TIMING
                        delay:0
                      options:UIViewAnimationOptionBeginFromCurrentState
                   animations:^{scrollView.frame = CGRectMake(self.view.frame.size.width - PANEL_WIDTH, 0, self.view.frame.size.width, self.view.frame.size.height);
                   }
                   completion:^(BOOL finished) {
                     if (finished) {
                       //[self resetMainView];
                       NSLog(@"Here");
                     }
                   }];
}

- (void)movePanelToOriginalPosition
{
  [UIView animateWithDuration:SLIDE_TIMING delay:0 options:UIViewAnimationOptionBeginFromCurrentState
                   animations:^{
                     scrollView.frame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height);
                   }
                   completion:^(BOOL finished) {
                     if (finished) {
                       
                       [self resetMainView];
                     }
                   }];
}

- (MainViewController*) init {
  //
  self = [super init];
  return self;
}


- (void)applyNewIndex:(NSInteger)newIndex pageController:(CenterViewController *)centerVC
{
	NSInteger pageCount = 3;// [[DataSource sharedDataSource] numDataPages];
	BOOL outOfBounds = newIndex >= pageCount || newIndex < 0;
  
	if (!outOfBounds)
	{
		CGRect pageFrame = centerVC.view.frame;
		pageFrame.origin.y = 0;
		pageFrame.origin.x = scrollView.frame.size.width * newIndex;
		centerVC.view.frame = pageFrame;
	}
	else
	{
		CGRect pageFrame = centerVC.view.frame;
		pageFrame.origin.y = scrollView.frame.size.height;
		centerVC.view.frame = pageFrame;
	}
  
	centerVC.pageIndex = newIndex;
}

- (void)scrollViewDidScroll:(UIScrollView *)sender
{
  CGFloat pageWidth = scrollView.frame.size.width;
  float fractionalPage = scrollView.contentOffset.x / pageWidth;
	
	NSInteger lowerNumber = floor(fractionalPage);
	NSInteger upperNumber = lowerNumber + 1;
	
	if (lowerNumber == currentPage.pageIndex)
	{
		if (upperNumber != nextPage.pageIndex)
		{
			[self applyNewIndex:upperNumber pageController:nextPage];
		}
	}
	else if (upperNumber == currentPage.pageIndex)
	{
		if (lowerNumber != nextPage.pageIndex)
		{
			[self applyNewIndex:lowerNumber pageController:nextPage];
		}
	}
	else
	{
		if (lowerNumber == nextPage.pageIndex)
		{
			[self applyNewIndex:upperNumber pageController:currentPage];
		}
		else if (upperNumber == nextPage.pageIndex)
		{
			[self applyNewIndex:lowerNumber pageController:currentPage];
		}
		else
		{
			[self applyNewIndex:lowerNumber pageController:currentPage];
			[self applyNewIndex:upperNumber pageController:nextPage];
		}
	}
	
	[currentPage updateTextViews:NO];
	[nextPage updateTextViews:NO];
}


- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)newScrollView
{
  CGFloat pageWidth = scrollView.frame.size.width;
  float fractionalPage = scrollView.contentOffset.x / pageWidth;
	NSInteger nearestNumber = lround(fractionalPage);
  
	if (currentPage.pageIndex != nearestNumber)
	{
		CenterViewController *swapController = currentPage;
		currentPage = nextPage;
		nextPage = swapController;
	}
  
	[currentPage updateTextViews:YES];
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)newScrollView
{
	[self scrollViewDidEndScrollingAnimation:newScrollView];
	pageControl.currentPage = currentPage.pageIndex;
}

- (IBAction)changePage:(id)sender
{
	NSInteger pageIndex = pageControl.currentPage;
  
	// update the scroll view to the appropriate page
  CGRect frame = scrollView.frame;
  frame.origin.x = frame.size.width * pageIndex;
  frame.origin.y = 0;
  [scrollView scrollRectToVisible:frame animated:YES];
}

@end
