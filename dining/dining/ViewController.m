//
//  ViewController.m
//  dining
//
//  Created by Nolan Miller on 10/12/13.
//  Copyright (c) 2013 Nolan Miller. All rights reserved.
//

#import "ViewController.h"
#import "DiningHall.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    _hallS = 0;
    
    [super viewDidLoad];
    connect = [[NSURLConnection alloc] initWithRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[DiningHall getPathForHall:EVK onMeal:Lunch]]] delegate:self startImmediately:NO];
    Udata= [[NSMutableData alloc] init];
    
    UISwipeGestureRecognizer * next = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipeLeft:)];
    UISwipeGestureRecognizer * prev = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipeRight:)];
    next.direction = UISwipeGestureRecognizerDirectionRight;
    prev.direction = UISwipeGestureRecognizerDirectionLeft;
    [self.webView addGestureRecognizer:next];
    [self.webView addGestureRecognizer:prev];
	// Do any additional setup after loading the view, typically from a nib.
}
- (void)swipeLeft:(UISwipeGestureRecognizer*)trigger{
    _hallS++;
    _hallS%=3;
    NSLog(@"trigged left");
}
- (void)swipeRight:(UISwipeGestureRecognizer*)trigger{
    _hallS+=2;
    _hallS%=3;
    NSLog(@"trigged right");
}
- (IBAction)buttonPressed:(id)sender {
    DiningHall * test = [[DiningHall alloc] initWithDiningHall:EVK];
    [test loadData];
    NSLog(@"%@",[NSURL URLWithString:[DiningHall getPathForHall:EVK onMeal:Lunch]]);
    
    self.webView.backgroundColor = [UIColor blackColor];
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[DiningHall getPathForHall:_hallS onMeal:[self.mealS selectedSegmentIndex]+1]]]];
    [Udata setData:NULL];
    [connect start];
}
-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data{
    [Udata appendData:data];
    NSLog(@"blip");
}
-(void)connectionDidFinishLoading:(NSURLConnection *)connection{
    NSLog(@"got here");
    //    [self.webView loadData:; MIMEType:<#(NSString *)#> textEncodingName:<#(NSString *)#> baseURL:<#(NSURL *)#>]
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
