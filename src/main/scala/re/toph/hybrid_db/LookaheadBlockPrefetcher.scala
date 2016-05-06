package re.toph.hybrid_db

import java.sql.Connection
import java.util.HashMap
//
//import scala.collection.mutable.ListBuffer
//import anorm.{ SQL, SqlParser }
//
///**
//  * Created by christoph on 28/04/16.
//  */
//
//
//class LookaheadBlockPrefetcher(blocksize: Long) extends Prefetcher {
//
//  override def get(k: Long)(implicit connection : Connection): (GraphNode, List[GraphNode]) = {
//
//    SQL(
//    """
//      | SELECT * FROM points
//      | JOIN edges
//      |   ON edges.id1=points.id
//      | WHERE lat>{minlat} AND lat<{maxlat} AND lng<{maxlng} AND lng>{minlng};
//    """.stripMargin
//    )
//
//    // TODO: Actually use the "hops" parameter
//    val resultSet = Timer.time("DB", {statement.executeQuery("(SELECT points.id, lat, lng, edges.id1, edges.id2, edges.dist FROM edges JOIN points ON points.id=edges.id1 WHERE id1=" + k + ") UNION (SELECT points.id, lat, lng, B.id1, B.id2, B.dist FROM edges A JOIN edges B ON A.id2=B.id1 JOIN points ON points.id=B.id1 WHERE A.id1=" + k + ")")} )
//
//    // TODO: I don't like blocking on prefetching. Can we do prefetch in a BG thread or something?
//
//    val ess:HashMap[Long, (ListBuffer[Edge], HashMap[String, Object])] = new HashMap[Long, (ListBuffer[Edge], HashMap[String, Object])]
//
//    while (resultSet.next()) {
//
//      // create a hashmap - this contains both node and edge data, but context will ignore irrelevants
//      val map = getMap(resultSet)
//
//      // create un-edged initial node if necessary
//      if (! ess.containsKey(resultSet.getLong("id1"))) {
//        ess.put(resultSet.getLong("id1"), (ListBuffer[Edge](), map))
//      }
//
//      ess.get(resultSet.getLong("id1"))._1 += Edge(resultSet.getLong("id1"), resultSet.getLong("id2"), map)
//    }
//
//    val ns = ListBuffer[GraphNode]()
//    var n : GraphNode = null
//    import scala.collection.JavaConversions._
//    ess.foreach( (x:(Long, (ListBuffer[Edge], HashMap[String, Object]))) => {
//      val (_k, (es, map)) = x
//      val _n = new GraphNode(_k, es.toList, map)
//      if (k==_k) n = _n
//      ns += _n
//    })
//
//    (n, ns.toList)
//  }
//}