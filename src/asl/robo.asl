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
        move(math.random(54) - 54, math.random(64) - 32);
        -beforeKickoff(true);
        !play.

@p2			  
+!play
    :
          not seeBall(Mb,Db)
    <-
        turn(40);
        !play.

@p3
+!play
    :
          seeBall(Mb,Db)
        & not aligned(Mb)
        & not inKickRange(Db)
	<-
        turn(Mb);
        !play.

@p4
+!play
    :
          seeBall(Mb,Db)
        & aligned(Mb)
        & not inKickRange(Db)
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
    <-
        kick(100,Mg);
        !play.
