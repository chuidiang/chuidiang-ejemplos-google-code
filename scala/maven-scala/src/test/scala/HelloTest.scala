

import org.scalatest._
import org.scalatest.Matchers

/*
 * @author Chuidiang
 */
class HelloTest extends FlatSpec with Matchers {
   "The sum 1+4" should "be 5 " in {
     var a = new Adder
     a.add(1.0,4.0) should be (5)
   }
   
   "The sum 3+3" should "be 6 " in {
     var a = new Adder
     a.add(3,3) should be (6)
   }
}