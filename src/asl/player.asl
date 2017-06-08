// Player agent to interact with Robocup

/* Initial beliefs and rules */
seeball(false).
ballcentered(false).
inkickrange(false).
seegoal(false).
beforekickoff(true).

/* Initial goals */
!start.

/* Plans */
+!start 
	: beforekickoff(true)
	<- 
		move("");
		-beforekickoff(true).
			  
+!main 
	: seeball(false)
	<- 
		turn("");
		!main.
+!main
	: 
		seeball(true) 
		& not ballcentered(true)
	<-
		align("");
		!main.
		
+!main
	:
		seeball(true)
		& ballcentered(true)
		& not inkickrange(true)
	<-
		dash("");
		!main.
		
+!main
	:
		seeball(true)
		& ballcentered(true)
		& inkickrange(true)
		& not seegoal(true)
	<-
		turn("");
		!main.

+!main
	:
		seeball(true)
		& ballcentered(true)
		& inkickrange(true)
		& seegoal(true)
	<-
		kick("");
		!main.