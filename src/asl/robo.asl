// Player agent to interact with Robocup

/* Initial beliefs and rules */
inKickRange(D):- D <= 1.
aligned(M):- M == 0.

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
    <-
        .print("p2")
        turn(40);
        !play.

@p3
+!play
    :
          seeBall(M,D)
        & not aligned(M)
        & not inKickRange(D)
	<-
        .print("p3")
        turn(M);
        !play.

@p4
+!play
    :
          seeBall(M,D)
        & aligned(M)
        & not inKickRange(D)
    <-
        .print("p4")
        dash(D);
        !play.

@p5
+!play
    :
          seeBall(M,D)
        & inKickRange(D)
        & not seeGoal(true)
	<-
	    .print("p5")
		turn(40);
		!play.

@p6
+!play
    :
           seeBall(M,D)
        &  inKickRange(D)
        &  seeGoal(true)
    <-
        .print("p6")
        kick("");
        !play.
