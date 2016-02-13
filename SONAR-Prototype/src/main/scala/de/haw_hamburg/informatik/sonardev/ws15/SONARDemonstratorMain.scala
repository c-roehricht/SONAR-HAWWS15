package de.haw_hamburg.informatik.sonardev.ws15

import de.haw_hamburg.informatik.sonardev.ws15.OPActor.Assign
import de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction.TFVForest
import de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction.TFVPlace
import de.haw_hamburg.informatik.sonardev.ws15.setUp.IOrganisationLoader
import de.haw_hamburg.informatik.sonardev.ws15.setUp.XmlOrganisationLoader
import akka.actor.ActorSystem

object SONARDemonstratorMain extends App {
  val system = ActorSystem("MyActorSystem")
  //val pingActor = system.actorOf(PingActor.props, "pingActor")
  //pingActor ! PingActor.Initialize
  // This example app will ping pong 3 times and thereafter terminate the ActorSystem - 
  // see counter logic in PingActor
  val orgLoader: IOrganisationLoader = new XmlOrganisationLoader
  val Org: SonarOrganisation = orgLoader.loadOrganisation("res/ExampleSmarthome.xml")

  import scala.collection.JavaConversions._
  val opactors = Org.getOpas.map(opa => system.actorOf(OPActor.props, opa.getName))
  //Compute the initial nondeterministic global team formation view and acquire a root
  val initialGlobalView: TFVForest = new TFVForest

  for (opactor <- opactors) {
    try {
      initialGlobalView.mergeIn(opactor.opa.getTeamFormationView)
    }
    catch {
      case e: TFVForest#TFVForestMergeException => {
        e.printStackTrace
      }
    }
  }
  val root: TFVPlace = initialGlobalView.getRoots.get(0)
  //Start the agent that is responsible for the root

  for (opactor <- opactors) {
    if (opactor.opa.getName == root.getTask.getOperator) {
      opactor ! Assign(root.getTask)
    }
  }
  system.awaitTermination()
}