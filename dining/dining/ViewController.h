//
//  ViewController.h
//  dining
//
//  Created by Nolan Miller on 10/12/13.
//  Copyright (c) 2013 Nolan Miller. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <NSURLConnectionDataDelegate>{
    NSURLConnection * connect;
    NSMutableData * Udata;
    int _hallS;
}
@property (weak, nonatomic) IBOutlet UIWebView *webView;
@property (weak, nonatomic) IBOutlet UISegmentedControl *mealS;

@end
