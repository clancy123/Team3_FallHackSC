//
//  LeftViewController.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "LeftViewController.h"
#include "hallCell.h"
#include "CenterViewController.h"
#include "diningHallStore.h"

@interface LeftViewController () {
  //UITableView *myTableView;
  //UITableViewCell *cellMain;
  NSMutableArray *arrayOfDiningHalls;
  diningHallStore *store;
}

@property (nonatomic, strong) hallCell *cellMain;
@property (nonatomic, strong) IBOutlet UITableView *myTableView;
//@property (nonatomic, weak) IBOutlet UITableViewCell *cellMain;

@end

@implementation LeftViewController
static NSNotificationCenter *SYNotificationCenter;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (id)init{
  store = [[diningHallStore alloc] init];
  self = [super init];
  //self.view.backgroundColor = [UIColor greenColor];
  self.myTableView = [[UITableView alloc] initWithFrame:CGRectMake(20, 20, 200, 400) style:UITableViewStylePlain];
  //self.myTableView.backgroundColor = [UIColor yellowColor];
  self.myTableView.dataSource = self;
  self.myTableView.delegate = self;
  
  [self setupHallsArray];
  
  self.view = self.myTableView;
  [self.myTableView reloadData];
  return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.myTableView reloadData];
  
    //self.view  = myTableView;
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)setupHallsArray
{
  /*
  NSArray *halls = @[
                       [diningHall itemWithTitle:@"EVK" withImage:[UIImage imageNamed:@"ID-100113060.jpg"]],
                       [diningHall itemWithTitle:@"Cafe84" withImage:[UIImage imageNamed:@"ID-10022760.jpg"]],
                       [diningHall itemWithTitle:@"IRC" withImage:[UIImage imageNamed:@"testnothing.jpg"]]
                       ];
  */
  arrayOfDiningHalls = [NSMutableArray arrayWithArray:store.diningHalls];
  
  [self.myTableView reloadData];
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
  return [arrayOfDiningHalls count];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
  return 54;
}


- (hallCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
  static NSString *cellID = @"cellMain";
  //hallCell *cell =
  
  self.cellMain = [tableView dequeueReusableCellWithIdentifier:cellID];
  
  if (self.cellMain == nil) {
    self.cellMain = [[hallCell alloc] init];
    // [[NSBundle mainBundle] loadNibNamed:@"MainCellLeft" owner:self options:nil];
  }
  
  UIImageView *mainImage = [[UIImageView alloc] init]; // (UIImageView *)[self.cellMain viewWithTag:1];
  
  UILabel *imageTitle = [[UILabel alloc] init]; //(UILabel *)[self.cellMain viewWithTag:2];
  
  NSString *textL = [[arrayOfDiningHalls objectAtIndex:indexPath.row] diningHallName];
  self.cellMain.label.text = textL;
  if ([arrayOfDiningHalls count] > 0)
  {
    diningHall *currentRecord = [arrayOfDiningHalls objectAtIndex:indexPath.row];
    
    mainImage.image = currentRecord.image;
    imageTitle.text = [NSString stringWithFormat:@"%@", currentRecord.diningHallName];
    NSLog(@"Image title: %@", imageTitle.text);
  }
  NSLog(@"Here is %@, and %@", textL, self.cellMain.label.text);
  
  return self.cellMain;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
  diningHall *currentRecord = [arrayOfDiningHalls objectAtIndex:indexPath.row];
  
  self.selected = currentRecord;
  [[NSNotificationCenter defaultCenter] postNotificationName:@"Reload"
                                                      object:self];
  
  // Return Data to delegate: either way is fine, although passing back the object may be more efficient
  // [_delegate imageSelected:currentRecord.image withTitle:currentRecord.title withCreator:currentRecord.creator];
  // [_delegate animalSelected:currentRecord];
  
  // [_delegate diningSelected:currentRecord];
  
  
  /*
  CenterViewController *centerVC = [[CenterViewController alloc] init];
  [centerVC setFoodArrayFromHall:currentRecord];
  [[self navigationController] pushViewController:centerVC animated:YES];
   */
  
}


@end
