//
//  CenterViewController.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "CenterViewController.h"
#include "foodCell.h"

@interface CenterViewController ()
{
  UIImageView *mainImageView;
  UILabel *imageTitle;
  NSMutableArray *foodArray;
  foodCell *cellMain;
  	BOOL textViewNeedsUpdate;
  //UITableView *foodTableView;

}

@property (nonatomic, strong) IBOutlet UITableView *foodTableView;

@end

@implementation CenterViewController
@synthesize pageIndex;
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(id)init {
  foodArray = [[NSMutableArray alloc] init];
  self = [super init];
   self.view.backgroundColor = [UIColor whiteColor];
  
  
  self.navBar = [[UINavigationBar alloc] init];
  [self.navBar setTintColor:[UIColor redColor]];
  [self.view addSubview:self.navBar];
  
  self.leftButton = [UIButton buttonWithType:UIButtonTypeCustom];
  
  UIImage *buttonImage = [UIImage imageNamed:@"menu.png"];
  [self.leftButton setBackgroundImage:buttonImage forState:UIControlStateNormal];
  
  [self.leftButton addTarget:self
                 action:@selector(btnMovePanelRight)
       forControlEvents:UIControlEventTouchDown];

  self.leftButton.frame = CGRectMake(20, 28, 26, 26);
  [self.view addSubview:self.leftButton];
  
  self.foodTableView = [[UITableView alloc] initWithFrame:CGRectMake(10, 100, self.view.frame.size.width - 20, [foodArray count] * 50) style:UITableViewStylePlain]; // UITableViewStyleGrouped
  
  // self.foodTableView.backgroundColor = [UIColor blueColor];
  [self.view addSubview:self.foodTableView];
  
  self.dayLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 20, self.view.frame.size.width, 40)];
  self.dayLabel.textAlignment = NSTextAlignmentCenter;
  self.dayLabel.text = @"Day Label";
  self.dayLabel.font=[UIFont fontWithName:@"Helvetica Neue" size:24];

  [self.view addSubview:self.dayLabel];
  
  self.foodTableView.dataSource = self;
  self.foodTableView.delegate = self;
  return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(reload)
                                               name:@"centerVCReload"
                                             object:nil];
  [self.foodTableView reloadData];
  
	// Do any additional setup after loading the view.
}

-(void)reload {
  NSLog(@"Need to reload here");
  NSLog(@"test");
  self.leftButton.tag = 1;
  [self setFoodArrayFromHall];
  [self.foodTableView reloadData];
  self.foodTableView.frame = CGRectMake(10, 100, self.view.frame.size.width - 20, [foodArray count] * 55);
  [self btnMovePanelRight];
}

- (void)viewDidUnload
{
  [super viewDidUnload];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)imageSelected:(UIImage *)image withTitle:(NSString *)title
{
  // only change the main display if an animal/image was selected
  if (image)
  {
    mainImageView.image = image;
    imageTitle.text = [NSString stringWithFormat:@"%@", title];
  }
}


- (void)diningHallSelected:(diningHall *)dining
{
  // only change the main display if an animal/image was selected
  if (dining)
  {
    [self showHallSelected:dining];
  }
}

- (void)showHallSelected:(diningHall *)selected
{
  mainImageView.image = selected.image;
  imageTitle.text = [NSString stringWithFormat:@"%@", selected.diningHallName];
}

- (void)btnMovePanelRight {
  NSLog(@"Move a view");
  NSLog(@"%d", self.leftButton.tag);
  switch(self.leftButton.tag) {
    case 0: {
      self.leftButton.tag = 1;
      NSLog(@"*** MOVE TO RIGHT ***");
      [_delegate movePanelRight];
      NSLog(@"new tag: %d", self.leftButton.tag);
      break;
    }
    case 1: {
      self.leftButton.tag = 0;
      NSLog(@"*** MOVE TO ORIGINAL ***");
      [_delegate movePanelToOriginalPosition];
      NSLog(@"new tag: %d", self.leftButton.tag);
      break;
    }
    default:
      break;
  }
}

- (void)btnMovePanelLeft {
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
  NSLog(@"There are %d", [foodArray count]);
  return [foodArray count];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
  return 54;
}

- (foodCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
  static NSString *cellID = @"cellMain";
  //hallCell *cell =
  
  cellMain = [tableView dequeueReusableCellWithIdentifier:cellID];
  
  if (cellMain == nil) {
    cellMain = [[foodCell alloc] init];
    // [[NSBundle mainBundle] loadNibNamed:@"MainCellLeft" owner:self options:nil];
  }
  
  // UIImageView *mainImage = [[UIImageView alloc] init]; // (UIImageView *)[self.cellMain viewWithTag:1];
  
  // UILabel *imageName = [[UILabel alloc] init]; //(UILabel *)[self.cellMain viewWithTag:2];
  
  if ([foodArray count] > 0)
  {
  Meal *currentMeal = [foodArray objectAtIndex:indexPath.row];
  NSString *textL = [currentMeal title];
  NSString *picText = [currentMeal foodPic];
  NSLog(@"Food is %@, image is %@", textL, picText);
  cellMain.label.text = textL;
  cellMain.pic = [UIImage imageNamed:picText];
    
  UIImage *photo = [UIImage imageNamed:picText];
  cellMain.pic = photo;
  

  //foodCell *cell = [foodCell initWIthNa
    
  // imageName.text = [NSString stringWithFormat:@"%@", currentMeal.title];

 // UIImageView *view = [[UIImageView alloc] initWithImage:[UIImage imageNamed:imageName.text]];
    
  //UIImage *image = [UIImage imageNamed:imageName.text];
  //cellMain.pic = image;
 // cellMain = [cellMain updatePic:imageName.text];
  //cellMain.pic = image;
    
  // mainImage.image = [UIImage imageNamed: currentMeal.foodPic];
    
    // UIImageView *view = [[UIImageView alloc] initWithImage:[UIImage imageNamed:imageName.text]];
    // NSLog(@"Image title: %@", imageTitle.text);
  }
 // NSLog(@"Here is %@, and %@", textL, cellMain.label.text);
  [cellMain setNeedsDisplay];
  return cellMain;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
  Meal *currentMeal = [foodArray objectAtIndex:indexPath.row];
  //diningHall *currentRecord = [arrayOfDiningHalls objectAtIndex:indexPath.row];
  
  // Return Data to delegate: either way is fine, although passing back the object may be more efficient
  // [_delegate imageSelected:currentRecord.image withTitle:currentRecord.title withCreator:currentRecord.creator];
  // [_delegate animalSelected:currentRecord];
  NSLog(@"Selected %@", currentMeal.title);
  // [_delegate mealSelected:currentMeal];
}

-(void)setFoodArrayFromHall {
  NSInteger mealNum = [[self.selectedHall getMeals] count];
  foodArray = [[NSMutableArray alloc] initWithCapacity:mealNum];
  for (NSInteger i = 0; i < mealNum; i++)
    foodArray[i] = [self.selectedHall getMeals][i];
  [self.foodTableView reloadData];
}

- (void)setPageIndex:(NSInteger)newPageIndex
{
	pageIndex = newPageIndex;
	
	if (pageIndex >= 0 && pageIndex < 3)
	{
    /*
		NSDictionary *pageData =
    [[DataSource sharedDataSource] dataForPage:pageIndex];
		label.text = [pageData objectForKey:@"pageName"];
		textView.text = [pageData objectForKey:@"pageText"];
		
		CGRect absoluteRect = [self.view.window
                           convertRect:textView.bounds
                           fromView:textView];
		if (!self.view.window ||
        !CGRectIntersectsRect(
                              CGRectInset(absoluteRect, TEXT_VIEW_PADDING, TEXT_VIEW_PADDING),
                              [self.view.window bounds]))
		{
			textViewNeedsUpdate = YES;
		}
     */
    textViewNeedsUpdate = YES;
	}
}

- (void)updateTextViews:(BOOL)force
{
  /*
	if (force ||
      (textViewNeedsUpdate &&
       self.view.window &&
       CGRectIntersectsRect(
                            [self.view.window
                             convertRect:CGRectInset(textView.bounds, TEXT_VIEW_PADDING, TEXT_VIEW_PADDING)
                             fromView:textView],
                            [self.view.window bounds])))
	{
		for (UIView *childView in textView.subviews)
		{
			[childView setNeedsDisplay];
		}
		textViewNeedsUpdate = NO;
	}
   */
}

-(void)setHall:(diningHall *)hall {
  self.selectedHall = hall;
}

@end
