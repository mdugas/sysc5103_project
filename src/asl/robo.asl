// Player agent to interact with Robocup

/* Initial beliefs and rules */

// The agent believes we are before the kickoff
beforeKickoff(true).

/* Initial goals */
!play.

/* Plans */
@p1
+!play
    : beforeKickoff(true)
    <-
        .print("Before Kickoff");
        move("");
        -beforeKickoff(true);
        !play.

@p2			  
+!play
    :
          not seeBall(M,D)
        & not ballAligned(true)
        & not inKickRange(true)
    <-
        .print("p2")
        turn(40);
        !play.

@p3
+!play
    :
              seeBall(M,D)
        & not ballAligned(true)
        & not inKickRange(true)
	<-
        .print("p3")
        turn(M);
        !play.

@p4
+!play
    :
          seeBall(M,D)
        & ballAligned(true)
        & not inKickRange(true)
    <-
        .print("p4")
        dash(D);
        !play.

@p5
+!play
    :
              seeBall(M,D)
        & not seeGoal(G)
        &     inKickRange(true)
	<-
	    .print("p5")
		turn(40);
		!play.

@p6
+!play
    :
           seeBall(M,D)
        &  seeGoal(G)
        &  inKickRange(true)
    <-
        .print("p6")
        kick(G);
        !play.
