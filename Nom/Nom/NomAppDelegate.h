//
//  NomAppDelegate.h
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MainViewController;
@class LeftViewController;
@interface NomAppDelegate : UIResponder <UIApplicationDelegate>
{
  MainViewController *viewController;
  UIWindow *window;
}

//@property (strong, nonatomic) UIWindow *window;
// @property (strong, nonatomic) MainViewController *viewController;
@property (nonatomic, strong) UIPageControl *pageControl;
@property(nonatomic, retain) NSMutableArray *viewControllers;
//@property (strong, nonatomic) LeftViewController *viewController;

@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;


- (void)saveContext;
- (NSURL *)applicationDocumentsDirectory;

@end
