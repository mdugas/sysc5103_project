// Player agent to interact with Robocup

/* Initial beliefs and rules */
inKickRange(D):- D <= 1.
aligned(M):- M == 0.

// The agent believes we are before the kickoff
//beforeKickoff(true).

/* Initial goals */
!play.

/* Plans */
@p0
+!play
    : 
    	gameMode(before_kick_off)
      & seeBall(Mb,Db)
      & aligned(Mb)
    <-
        turn(0);
        !play.

@p1
+!play
    : 
       not inField
    <-
        .print("Before Kickoff");
        move(math.random(54) - 54, math.random(64) - 32);
        +inField;
        !play.

@p2			  
+!play
    :
          not seeBall(Mb,Db)
    <-
        if (ballLastSeenLeft) {
        	turn(-40);
        } else {
        	turn(40);
        }
        !play.

@p3
+!play
    :
          seeBall(Mb,Db)
        & not aligned(Mb)
        & not inKickRange(Db)
	<-
        if (Mb < 0) {
        	+ballLastSeenLeft;
        } else {
        	-ballLastSeenLeft;
        }
        turn(Mb);
        !play.

@p4
+!play
    :
          seeBall(Mb,Db)
        & aligned(Mb)
        & not inKickRange(Db)
        & not gameMode(before_kick_off)
    <-
        dash(10 * Db);
        !play.

@p5
+!play
    :
          seeBall(Mb,Db)
        & inKickRange(Db)
        & not seeGoal(Mg,Dg)
	<-
		turn(40);
		!play.

@p6
+!play
    :
           seeBall(Mb,Db)
        &  inKickRange(Db)
        &  seeGoal(Mg,Dg)
        &  not gameMode(before_kick_off)
    <-
        kick(100,Mg);
        !play.   
		