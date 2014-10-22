package concurrency.akka.account

import akka.actor.{ActorRef, Props, Actor, ActorLogging}

class AccountManager extends Actor with ActorLogging {
   val assistants = (1 to 5).map {
     i => context.actorOf(Props[AccountAssistant], s"assistant$i")
   }

   var current = 0

   def next: ActorRef = {
     if (current < 4) {
       current += 1
     } else {
       current = 0
     }
     assistants(current)
   }

   override def receive: Receive = {
     case c: Create => next ! c
     case Report => assistants.foreach(_ ! Report)
   }
 }
