package complementos;

public class Pair<first, second>{
	   private first first;
	   private second second;

	   public Pair(first first, second second){
	     this.first = first;
	     this.second = second;
	   }

	   public void setFirst(first first){
	    this.first = first;
	   }

	   public void setSecond(second second) {
	     this.second = second;
	   }

	   public first getFirst() {
	     return this.first;
	   }

	   public second getSecond() {
	     return this.second;
	   }
	}