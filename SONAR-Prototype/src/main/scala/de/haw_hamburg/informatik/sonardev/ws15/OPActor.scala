package de.haw_hamburg.informatik.sonardev.ws15

import akka.actor.{Actor, ActorLogging, Props}
import de.haw_hamburg.informatik.sonardev.ws15.agents.{OpaDataSource, Opa}
import de.haw_hamburg.informatik.sonardev.ws15.data.AssignedTask
;

class OPActor(var opa: Opa) extends Actor with ActorLogging with OpaDataSource{
  import OPActor._

  opa.setDataSource(this)

  def receive = {
    case Assign(task) =>
      opa.assign(task)
  }

  override def assignToAgent(agent: String, task: AssignedTask): Unit = {
    context.actorOf(props, agent) ! Assign(task)
  }
}

object OPActor {
  val props = Props[OPActor]
  case class Assign(task: AssignedTask)
}
