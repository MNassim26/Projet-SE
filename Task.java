public class Task {
	//duration
		private int d ;
		
		//priority
		private int p; 
		
		//temps d'arrivï¿½e
		private int temps_arriv;
		
		//temps dï¿½but
			private int temps_deb; 
			
			
		
		//temps final
		private int temps_final;
		 
		
		//nom du process
		private String processname;
		
		
		
		//pi/di
		private float pidi ; 
		
		private String status = "Pas encore déposé";
		
		public Task() {
			
		}
		
		public int getTemps_deb() {
			return temps_deb;
		}



		public void setTemps_deb(int temps_deb) {
			this.temps_deb = temps_deb;
		}



		public int getTemps_final() {
			return temps_final;
		}



		public void setTemps_final(int temps_final) {
			this.temps_final = temps_final;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}



		public Task(int d , int p , int temps_arriv, String name) {
			this.d = d ; 
			this.p = p; 
			this.temps_arriv = temps_arriv;
		    this.pidi =(float) p / d ;
		    this.temps_deb = -1;
		    processname = name;
		}
		


		public int getD() {
			return d;
		}

		public void setD(int d) {
			this.d = d;
		    pidi = d != 0 ? p / this.d : 0; 
		}

		public int getP() {
		
			return p;
		}

		public void setP(int p) {
			this.p = p;
		}

		public int getTemps_arriv() {
			return temps_arriv;
		}

		public void setTemps_arriv(int temps_arriv) {
			this.temps_arriv = temps_arriv;
		}



		public float getPidi() {
			return pidi;
		}



		public void setPidi(float pidi) {
			this.pidi = pidi;
		}
	   
		 
		


		public String getProcessname() {
			return processname;
		}

		public void setProcessname(String processname) {
			this.processname = processname;
		}

		@Override
		public String toString() {
			return "Processus [ nom du processus= " + processname  + " durée= " + d + " , priorité= " + p + " , temps_arriv= " + temps_arriv + " ,  temps_final= " + temps_final +  " , pidi= " + pidi + " , statut= "
					+ status + "]";
		}




		

}
