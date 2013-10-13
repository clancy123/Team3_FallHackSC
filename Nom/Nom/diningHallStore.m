//
//  diningHallStore.m
//  Nom
//
//  Created by Kaitlyn Lee on 10/12/13.
//  Copyright (c) 2013 Kaitlyn Lee. All rights reserved.
//

#import "diningHallStore.h"
#include "diningHall.h"

@implementation diningHallStore

-(id)init {
  self = [super init];
  diningHall *evk = [[diningHall alloc] initWithTitle:@"EVK" withImage:[UIImage imageNamed:@"image.png"]];
  [evk addMeal:@"Shrimpanzee" withImage:@"shrimp.jpg-large"];
  [evk addMeal:@"Cheespider" withImage:@"Cheespider.jpg"];
  diningHall *irc = [[diningHall alloc] initWithTitle:@"IRC" withImage:[UIImage imageNamed:@"image.png"]];
  [irc addMeal:@"Tacodile Supreme" withImage:@"tacodile.jpg"];
  [irc addMeal:@"Mosquitoast" withImage:@"toast.jpg"];
  diningHall *cafe84 = [[diningHall alloc] initWithTitle:@"Cafe 84" withImage:[UIImage imageNamed:@"image.png"]];
  [cafe84 addMeal:@"Hippotatomous" withImage:@"Hippo.png"];
  [cafe84 addMeal:@"Fruit cockatiel" withImage:@"fruit.jpg"];
  self.diningHalls = [[NSMutableArray alloc] initWithObjects:evk, irc, cafe84, nil];
  return self;
}

@end
