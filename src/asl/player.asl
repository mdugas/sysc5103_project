// Player agent to interact with Robocup

/* Initial beliefs and rules */

// The agent believes we are before the kickoff
beforeKickoff(true).

/* Initial goals */
!play(x).

/* Plans */
@p1
+!play(x)
	: beforeKickoff(true)
	<- 
		.print("Before Kickoff");
		move("");
		-beforeKickoff(true);
		!play(true).

@p2			  
+!play(x)
	:
		  ~seeBall(true)
		& ~ballAligned(true)
		& ~inKickRange(true)
	<-
		turn("");
		!play(true).

@p3		
+!play(x)
	:
		   seeBall(true)
		& ~ballAligned(true)
		& ~inKickRange(true)
	<-
		align("");
		!play(true).

@p4		
+!play(x)
	:
		   seeBall(true)
		&  ballAligned(true)
		& ~inKickRange(true)
	<-
		dash("");
		!play(true).

@p5		
+!play(x)
	:
		   seeBall(true)
		& ~seeGoal(true)
		&  ballAligned(true)
		&  inKickRange(true)
	<-
		turn("");
		!play(true).

@p6
+!play(x)
	:
		   seeBall(true)
		&  seeGoal(true)
		&  ballAligned(true)
		&  inKickRange(true)
	<-
		kick("");
		!play(true).
