object LearningScala2 {
  // Flow control
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.")
                                                  //> The world makes sense.
  
  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  }                                               //> The world makes sense.
  
  // Matching - like switch in other languages:
  val number = 3                                  //> number  : Int = 3
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	}                                         //> Three
 	
 	// For loops
 	for (x <- 1 to 10) {
 		val squared = x * x
 		println(squared)
 	}                                         //> 1
                                                  //| 4
                                                  //| 9
                                                  //| 16
                                                  //| 25
                                                  //| 36
                                                  //| 49
                                                  //| 64
                                                  //| 81
                                                  //| 100
                                                  
  // While loops
  var x = 10                                      //> x  : Int = 10
  while (x >= 0) {
  	println(x)
  	x -= 1
  }                                               //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
                                                  
  x = 0
  do { println(x); x+=1 } while (x <= 10)         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
   {val x = 10; x + 20}                           //> res0: Int = 30
                                                
	 println({val x = 10; x + 20})            //> 30
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	 {
		 var curVal = 1
		 var preVal = 0
		 print(preVal+", ")
		 print(curVal+", ")
		 for (x <- 3 to 10) {
		 		var total = curVal+preVal
		 		if(x==10){
		 			println(total)
		 		}else{
		 			print(total+", ")
		 		}
		 		
		 		preVal = curVal
	 			curVal = total
	 	}
 	}                                         //> 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	   
}