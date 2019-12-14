import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Desktop;
import java.nio.file.StandardOpenOption;


//Partie1: Implémentation des commandes Linux
//Partie2: Implémentation des algorithmes d'ordonnancement FCFS (FIFO), SRT,Tourniquet 
//et algorithme préemptif basé sur la priorité.
public class Projet{ 

	//Commandes
	
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String RED = "\u001B[31m";
	public static final String CYAN = "\u001B[36m";
	
	public static String newList = "";
    public static String newHelp = "";
    public static String newChangeDirectory = "";
    public static String newCreate = "";
    public static String newWriteOnFile = "";
    public static String newExecuteFile = "";
    public static String newRemoveFile = "";
    public static String newPrecedentDirectory="";

	public static void content() {
		String curDir = System.getProperty("user.dir");
		System.out.println ("$Le répertoire courant est: "+curDir);
		String chemin = curDir.replaceAll("/", "//");
		File dir = new File(chemin);
		File[] children = (File[]) dir.listFiles();
		if (children == null) {
		System.out.println("Le dossier n'existe pas.");
		} else {
		for (int i=0; i < children.length;i++){
		File fichier = children[i];
		System.out.println(fichier.getName());
		}
		}

	}
	
	static ArrayList<String> allFiles = new ArrayList<String>();
	static ArrayList<String> allRepertoire = new ArrayList<String>();
	static ArrayList<String> all = new ArrayList<String>();
	public static void getLsList(ArrayList<String> allFiles, ArrayList<String> allRepertoire) {
		String curDir = System.getProperty("user.dir");
		allFiles.clear();
		allRepertoire.clear();
		all.clear();
		String chemin = curDir.replaceAll("/", "//");
		File dir = new File(chemin);
		File[] children = (File[]) dir.listFiles();
		if (children == null) {
		System.out.println("Le dossier n'existe pas.");
		} else {
		for (int i=0; i < children.length;i++){
			File fichier = children[i];
			all.add(fichier.getName());
			if(fichier.getName().indexOf(".")!=-1) {
				allRepertoire.add(fichier.getName());
			}
			else {
				allFiles.add(fichier.getName());
			}
		}
		}
	}
	

	public static void sortLs() {
		getLsList(allFiles, allRepertoire);
		java.util.Collections.sort(all);
		for(String elem: all)
	       {
	       	 System.out.println (elem);
	       }
	}
	
	public static void lsPipe(String[] separated) {
		getLsList(allFiles, allRepertoire);
		switch(separated[2]) {
		case "countFiles":
				if (allFiles.size()==0) {
					System.out.println("Ce répértoire est vide!");
				} else {
					System.out.println("Ce répértoire contient "+allRepertoire.size()+ " fichiers.");
				}
				
				break;
		case "countRepo":
			if (allRepertoire.size()==0) {
				System.out.println("Ce répértoire est vide! ");
			} else {
				System.out.println("Ce répértoire contient "+allFiles.size()+ " dossiers.");
			}
			break;
		default:
			System.out.println("Commande invalide!");
			break;
		}			
			
	}
	//Récupérer home et path à partir du fichier
	public static void getPathAndHome() {
		try{
			InputStream flux=new FileInputStream("C:\\Users\\dabdoub85\\Desktop\\mini_projet_se\\Mini_Project_SE\\home&path.txt");
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			String path;
			String home;
			ligne=buff.readLine();
			path=ligne.substring(5);
			ligne=buff.readLine();
			home=ligne.substring(5);
			System.out.println(home);
			System.out.println(path);
			buff.close();
		}

			catch (Exception e){
			System.out.println(e.toString());
			}

	}

	
	public static void move(String newDirectory) {
		System.setProperty("user.dir", newDirectory);
	}
	
	public static void moveBack() {
		String curDir = System.getProperty("user.dir");
		int lastSlash = curDir.lastIndexOf("\\");
		System.setProperty("user.dir", curDir.substring(0, lastSlash));
	}
	
	//création d'un dossier
	public static void create(String filename) {
		String curDir = System.getProperty("user.dir");
		File f = new File(curDir+"\\"+filename);
        if (f.mkdir()) {
            System.out.println("Dossier créé.");
        }
        else {
            System.out.println("Impossible de créer le dossier.");
        }
	}

	public static void delete(String filename) {
		String curDir = System.getProperty("user.dir");
		File f = new File(curDir+"\\"+filename);
        if (f.delete()) {
            System.out.println("Dossier supprimé");
        }
        else {
            System.out.println("Impossible de supprimer le dossier.");
        }
	}

	public static void rename(String filename,String newfilename) {
		String curDir = System.getProperty("user.dir");
		File oldName = new File(curDir+"\\"+filename);
		File newName= new File(curDir+"\\"+newfilename);
        if (oldName.renameTo(newName)) {
            System.out.println("Renamed successfully");
        }
        else {
            System.out.println("Error");
        }
	}

	public static void writeOnANewFileWord(String fileName) throws IOException {
		String curDir = System.getProperty("user.dir");
		System.out.println(curDir);
		File file = new File(curDir+"\\"+fileName+".txt");
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("Fichié créé!");
		} else {
		    System.out.println("Fichié existe déjà!.");
		}
		try {
		     if (Desktop.isDesktopSupported()) {
		       Desktop.getDesktop().open(new File(file.getAbsolutePath()));
		     }
		   } catch (IOException ioe) {
		     ioe.printStackTrace();
		  }
	}

	public static void aide() {
		System.out.println(GREEN+"aide:"+RESET+"Donne des informations sur chaque commande. ");
		System.out.println(GREEN+"move Name:"+RESET+" permet de naviguer entre les dossiers.");
		System.out.println(GREEN+"moveBack :"+RESET+" permet de retourner au dossier précédent.");
		System.out.println(GREEN+"content:"+RESET+" Affiche les dossiers et les fichiers dans l'environnement de travail courant.");
		System.out.println(GREEN+"create Name:"+RESET+" Permet de créer un dossier ou un fichier.");
		System.out.println(GREEN+"delete Name: Permet de supprimer un dossier ou un fichier existant.");
		System.out.println(GREEN+"rename oldName newName:"+RESET+" Permet de changer le nom d'un dossier ou un fichier.");
		System.out.println(GREEN+"writeOnFile fileName:"+RESET+" Cree un nouveau fichier text et ouvre le fichier avec bloc note.");
		System.out.println(GREEN+"executeFile fileName:"+RESET+" Exécute les commandes qui se trouvent dans un fichier text.");
		System.out.println(GREEN+"alias oldName newName::"+RESET+" Permet de changer le nom d'une commande.");
	}

	public static void executeCommandsOnFile(String cmd) throws IOException {
		String curDir = System.getProperty("user.dir");
		String[] separated = cmd.split(" ");
		switch(separated[0]) {
		case "aide":
			aide();
			break;
		case "move":
			move(separated[1]);
			break;
		case "create":
			System.out.println("******************************");
			System.out.println(separated[1]);
			create(separated[1]);
			System.out.println(curDir);
			break;
		case "delete":
				delete(separated[1]);
				break;
        case "rename":
				rename(separated[1],separated[2]);
				break;
		case "content":
			content();
			break;
		case "write_on_file":
			writeOnANewFileWord(separated[1]);
			break;
		case "executeFile":
			executeFile(separated[1]);
			break;
		
		default:
			System.out.println("Commande invalide!");
		}	
	}

	public static void executeFile(String fileName) {
		String curDir = System.getProperty("user.dir");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(curDir+"\\"+fileName+".txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				executeCommandsOnFile(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}public static void alias(String[] separated) {
		switch(separated[1]) {
		case "aide":
			newHelp=separated[2];
			break;
		case "move":
			newChangeDirectory=separated[2];
			break;
		case "create":
			newCreate=separated[2];
			break;
		case "content":
			newList=separated[2];
			break;
		case "writeOnFile":
			newWriteOnFile=separated[2];
			break;
		case "removeFile":
			newRemoveFile=separated[2];
			break;
		case "executeFile":
			newExecuteFile=separated[2];
			break;
		case "precedentDirectory":
			newPrecedentDirectory=separated[2];
			break;
		default:
			System.out.println("Commande invalide!");
			break;
		}	
	}		
	
	public static void Redirection(String[] separated) throws IOException {
		String curDir = System.getProperty("user.dir");
		String chemin = curDir.replaceAll("/", "//");
			switch(separated[0]) {
			case "aide":
				if(separated[1].equals(">>")) {			
					File file = new File(curDir+"\\"+separated[2]+".txt");
					//Create the file
					if (file.createNewFile())
					{
					    System.out.println("Fichié créé!");
					    
					} else {
					    System.out.println("Fichié existe déjà!.");
					}		
					BufferedWriter  output = new BufferedWriter(new FileWriter(file,true));
					output.append("Aide: Des informations sur chaque commande.");
					output.newLine();
					output.append("move Name: permet de naviguer entre les dossiers.");
					output.newLine();
					output.append("moveBack : permet de retourner au dossier précédent.");
					output.newLine();
					output.append("Content: Affiche les les dossiers et les fichiers dans l'environnement de travail courant.");
					output.newLine();
					output.append("create Name: Permet de créer un dossier ou un fichier.");
					output.newLine();
					output.append("delete Name: Permet de supprimer un dossier ou un fichier existant.");
					output.newLine();
					output.append("rename oldName newName: Permet de changer le nom d'un dossier ou un fichier.");
					output.newLine();
					output.append("writeOnFile fileName: Cree un nouveau fichier text et ouvre le fichier avec bloc note.");
					output.newLine();
					output.append("executeFile fileName: Exécute les commandes qui se trouvent dans un fichier text.");
					output.newLine();
					output.append("alias oldName newName: Permet de changer le nom d'une commande.");
					output.close();
				} else if (separated[1].equals(">"))  {
					File file = new File(curDir+"\\"+separated[2]+".txt");
					//Create the file
					if (file.createNewFile())
					{
					    System.out.println("Fichié créé!");
					    
					} else {
					    System.out.println("Fichié existe déjà!.");
					}		
					BufferedWriter  output = new BufferedWriter(new FileWriter(file));
					output.append("Aide: Des informations sur chaque commande.");
					output.newLine();
					output.append("move Name: permet de naviguer entre les dossiers.");
					output.newLine();
					output.append("moveBack : permet de retourner au dossier précédent.");
					output.newLine();
					output.append("Content: Affiche les les dossiers et les fichiers dans l'environnement de travail courant.");
					output.newLine();
					output.append("create Name: Permet de créer un dossier ou un fichier.");
					output.newLine();
					output.append("delete Name: Permet de supprimer un dossier ou un fichier existant.");
					output.newLine();
					output.append("rename oldName newName: Permet de changer le nom d'un dossier ou un fichier.");
					output.newLine();
					output.append("writeOnFile fileName: Cree un nouveau fichier text et ouvre le fichier avec bloc note.");
					output.newLine();
					output.append("executeFile fileName: Exécute les commandes qui se trouvent dans un fichier text.");
					output.newLine();
					output.append("alias oldName newName: Permet de changer le nom d'une commande.");
					output.close();
				} else {
					System.out.println("Commande invalide!");
				}
				break;
			case "content":
				
				File dir = new File(chemin);
				File[] children = (File[]) dir.listFiles();
				if (children == null) {
				} else {
					if(separated[1].equals(">>")) {
						File file = new File(curDir+"\\"+separated[2]+".txt");
						//Create the file
						if (file.createNewFile())
						{
						    System.out.println("Fichié créé!");
						    
						} else {
						    System.out.println("Fichié existe déjà!.");
						}		
						BufferedWriter  output = new BufferedWriter(new FileWriter(file,true));		 
						for (int i=0; i < children.length;i++){
							File fichier = children[i];
							output.append(fichier.getName());
							output.newLine();
						}
						output.close();
					}
					else if (separated[1].equals(">")){
						File file = new File(curDir+"\\"+separated[2]+".txt");
						//Create the file
						if (file.createNewFile())
						{
						    System.out.println("Fichié créé!");
						    
						} else {
						    System.out.println("Fichié existe déjà!.");
						}		
						BufferedWriter  output = new BufferedWriter(new FileWriter(file));
						for (int i=0; i < children.length;i++){
							File fichier = children[i];
							output.append(fichier.getName());
							output.newLine();
						}
						output.close();
					}
					else if (separated[1].equals("|")) {
						lsPipe(separated);
					}
					else {
						System.out.println("Commande invalide!");
					}
				}
				break;
			default:
				System.out.println("Commande invalide!");
			}
			
		}
	//}
	
	public static void removeFile(String fileName) {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir+"\\"+fileName);
		if(file.delete()) 
        { 
            System.out.println("Le fichier a été supprimé avec succès."); 
        } 
        else
        { 
            System.out.println("Fichier innexistant."); 
        } 
    } 
	
	
	//Ordonnancement
	
	//FIFO
	
//Temps d'attente des processus
static void findWaitingTimeFifo(ArrayList<Integer> processes, int n, ArrayList<Integer> execution, int wt[], ArrayList<Integer> arrivee)  
{  
 int service_time[] = new int[n];  
 service_time[0] = 0;  
 wt[0] = 0;  

 // calculer le temps d'attente  
 for (int i = 1; i < n ; i++)  
 {  
     // Ajouter le temps d'exécution du processus précédent
     service_time[i] = service_time[i-1] + execution.get(i-1);  

     // trouver le temps d'attente du processus courant =  
     // sum - at[i]  
     wt[i] = service_time[i] - arrivee.get(i); 

     // If waiting time for a process is in negative  
     // that means it is already in the ready queue  
     // before CPU becomes idle so its waiting time is 0  
     if (wt[i] < 0)  
         wt[i] = 0;  
 }  
}  

//Calculer le temps de rotation  
static void findTurnAroundTimeFifo(ArrayList<Integer> processes, int n, ArrayList<Integer> execution,  
                                 int wt[], int tat[])  
{  
 // Calculer le temps de rotation: bt[i] + wt[i]  
 for (int i = 0; i < n ; i++)  
     tat[i] = execution.get(i) + wt[i];  
}  

//calculer le temps d'attente moyen et de rotation moyen
static void findavgTimeFifo(ArrayList<Integer> processes, int n, ArrayList<Integer> execution, ArrayList<Integer> arrivee)  
{  
 int wt[] = new int[n], tat[] = new int[n];  

 //Fonction pour trouver le temps d'attente de tous les processus
 findWaitingTimeFifo(processes, n, execution, wt, arrivee);  

 // Fonction pour trouver le temps de rotation de tous les processus
 findTurnAroundTimeFifo(processes, n, execution, wt, tat);  

 // Afficher les résultats trouvés  
 System.out.print("Process " + " T.exécution " + " Temps d'arrivée "
     + " Temps d'attente " + " Temps de rotation "
     + " Date de fin \n");  
 int total_wt = 0, total_tat = 0;  
 for (int i = 0 ; i < n ; i++)  
 {  
     total_wt = total_wt + wt[i];  
     total_tat = total_tat + tat[i];  
     int compl_time = tat[i] + arrivee.get(i);  
     System.out.println(i+1 + "\t\t" + execution.get(i) + "\t\t"
         + arrivee.get(i) + "\t\t" + wt[i] + "\t\t "
         + tat[i] + "\t\t " + compl_time);  
 }  

 System.out.print("\nTemps d'attente moyen = "
     + (float)total_wt / (float)n);  
 System.out.print("\nTemps de rotation moyen = "
     + (float)total_tat / (float)n);  
}
 
 //SRT
 
//Methode pour trouver le temps d'attente
 static void findWaitingTimeSrt(ArrayList<Process> process, int n, 
                                  int wt[]) 
 { 
     int rt[] = new int[n]; 
    
     // Copier le temps d'exécution dans rt[] 
     for (int i = 0; i < n; i++) 
         rt[i] = process.get(i).bt; 
    
     int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
     int shortest = 0, finish_time; 
     boolean check = false; 
    
     // Process jusqu'à achèvement de tous les processus
     while (complete != n) { 
    
         // Trouvez le procesus avec 
    	 //le plus petit temps d'exécution restant
         // parmi les processus prêts.
         for (int j = 0; j < n; j++)  
         { 
             if ((process.get(j).art <= t) && 
               (rt[j] < minm) && rt[j] > 0) { 
                 minm = rt[j]; 
                 shortest = j; 
                 check = true; 
             } 
         } 
    
         if (check == false) { 
             t++; 
             continue; 
         } 
    
         // diminuer le temps d'exécution restant d'une unité de temps.
         rt[shortest]--; 
    
         // Mise à jour du minimum
         minm = rt[shortest]; 
         if (minm == 0) 
             minm = Integer.MAX_VALUE; 
    
         // lorsqu'un processus termine son exécution
         if (rt[shortest] == 0) { 
    
             // Incrementer complete 
             complete++; 
             check = false; 
    
             // trouver finish time du processus courant
             finish_time = t + 1; 
    
             // Calculer le temps d'attente
             wt[shortest] = finish_time - 
                          process.get(shortest).bt - 
                          process.get(shortest).art; 
    
             if (wt[shortest] < 0) 
                 wt[shortest] = 0; 
         } 
         // Incrementer time t
         t++; 
     } 
 } 
    
 // Methode pour calculer le temps de rotation
 static void findTurnAroundTimeSrt(ArrayList<Process> process, int n, 
                         int wt[], int tat[]) 
 { 
     // calculer le temps de rotation: bt[i] + wt[i] 
     for (int i = 0; i < n; i++) 
         tat[i] = process.get(i).bt + wt[i]; 
 } 
    
 // Methode pour calculer le temps moyen
 static void findavgTimeSrt(ArrayList<Process> process, int n) 
 { 
     int wt[] = new int[n], tat[] = new int[n]; 
     int  total_wt = 0, total_tat = 0; 
    
     // Fonction pour trouver le tems d'attente de tous les processus
     findWaitingTimeSrt(process, n, wt); 
    
     // Fonction pour trouver le tems de rotation de tous les processus
     findTurnAroundTimeSrt(process, n, wt, tat); 
    
     // afficher les résultats
     System.out.println("Processus " + 
                        " Temps d'exécution " + 
                        " Temps d'attente " + 
                        " Temps de rotation"); 
    
     // Calcul du temps d'attente total et 
     // temps de rotation total
     for (int i = 0; i < n; i++) { 
         total_wt = total_wt + wt[i]; 
         total_tat = total_tat + tat[i]; 
         System.out.println(" " + process.get(i).pid + "\t\t"
                          + process.get(i).bt + "\t\t " + wt[i] 
                          + "\t\t" + tat[i]); 
     } 
    
     System.out.println("Temps d'attente moyenne = " + 
                       (float)total_wt / (float)n); 
     System.out.println("Temps de rotation moyenne = " + 
                        (float)total_tat / (float)n); 
 }
 
     
     //tourniquet
     
     
  // Methode pour trouver le temps d'attente de tous les processus 
     static void findWaitingTimeTourniquet(ArrayList<Integer> processes, int n, 
    		 ArrayList<Integer> execution, int wt[], int quantum) 
     { 
         // copier les temps d'exécution bt[] pour stocker les temps d'exécution restants
         int rem_bt[] = new int[n]; 
         for (int i = 0 ; i < n ; i++) 
             rem_bt[i] =  execution.get(i); 
        
         int t = 0; // Current time 
        
         // executer tous les processus jusqu'à achèvement 
         while(true) 
         { 
             boolean done = true; 
        
             // executer tous les processus un par un
             for (int i = 0 ; i < n; i++) 
             { 
                 if (rem_bt[i] > 0) 
                 { 
                     done = false;
        
                     if (rem_bt[i] > quantum) 
                     { 
                          t += quantum; 
        
                         // diminuer le temps d'execution par le quantum
                         rem_bt[i] -= quantum; 
                     } 
        
                     // si temps d'execution<quantum, le processus va terminer son execution.
                     else
                     { 
                         t = t + rem_bt[i]; 
        
                         wt[i] = t - execution.get(i); 
        
                         rem_bt[i] = 0; 
                     } 
                 } 
             } 
        
             // si tous les processus terminés
             if (done == true) 
               break; 
         } 
     } 
        
     // Methode pour calculer le temps de rotation
     static void findTurnAroundTimeTourniquet(ArrayList<Integer> processes, int n, 
    		 ArrayList<Integer> execution, int wt[], int tat[]) 
     { 
         // calculer le temps de rotation: bt[i] + wt[i] 
         for (int i = 0; i < n ; i++) 
             tat[i] = execution.get(i) + wt[i]; 
     } 
        
     // Method pour calculer le temps moyen
     static void findavgTimeTourniquet(ArrayList<Integer> processes, int n, 
    		 ArrayList<Integer> execution, int quantum) 
     { 
         int wt[] = new int[n], tat[] = new int[n]; 
         int total_wt = 0, total_tat = 0; 
        
         // Fonction pour trouver le temps d'attente de tous les processus
         findWaitingTimeTourniquet(processes, n, execution, wt, quantum); 
        
         // Fonction pour trouver le temps de rotation de tous les processus
         findTurnAroundTimeTourniquet(processes, n, execution, wt, tat); 
        
         // afficher les resultats
         System.out.println("Process " + " Temps d'exécution " + 
                       " attente " + " rotation"); 
        
         // Calculer le temps d'attente et le temps de rotation
         for (int i=0; i<n; i++) 
         { 
             total_wt = total_wt + wt[i]; 
             total_tat = total_tat + tat[i]; 
             System.out.println(" " + (i+1) + "\t\t" + execution.get(i) +"\t " + 
                               wt[i] +"\t\t " + tat[i]); 
         } 
        
         System.out.println("Temps d'attente moyen = " + 
                           (float)total_wt / (float)n); 
         System.out.println("Temps de rotation moyen = " + 
                            (float)total_tat / (float)n); 
     
     
 
}  
     //Préemptif
     
   //quick sort for temps d'arrivée
 	

 	public static void quickSortTempsArrive(ArrayList<Task> arr, int begin, int end) {
 	    if (begin < end) {
 	        int partitionIndex = partitionTempsArrive(arr, begin, end);
 	        ;
 	        quickSortTempsArrive(arr, begin, partitionIndex-1);
 	        quickSortTempsArrive(arr, partitionIndex+1, end);
 	    }
 	}
 	
 	
 	public static int partitionTempsArrive(ArrayList<Task> arr, int begin, int end) {
 	    Task pivot = arr.get(end);
 	    int i = (begin-1);
 	 
 	    for (int j = begin; j < end; j++) {
 	        if (arr.get(j).getTemps_arriv() <= pivot.getTemps_arriv()) {
 	            i++;
 	 
 	            Task swapTemp = arr.get(i);
 	            arr.set(i, arr.get(j)) ;
 	            arr.set(j, swapTemp);
 	        }
 	    }
 	 
 	    Task swapTemp = arr.get(i+1);
 	    arr.set(i+1, arr.get(end)) ;
 	    arr.set(end, swapTemp);
 	 
 	    return i+1;
 	}

 	
 	public  static void preemptif(ArrayList<Task> listTasks) { 
        
 		quickSortTempsArrive(listTasks , 0 , listTasks.size() - 1);
 		
 		
 		
 		int nextArrivalTime = listTasks.get(0).getTemps_arriv() ; 
 		int t_cpu = nextArrivalTime;
 		System.out.println(t_cpu);
 		
 		int index_to_start = 0;
 		int previous_index =0;
 		
 		while(true) {
 			
 			getListProcess(listTasks,t_cpu); 
 		    
 			previous_index = index_to_start;
 		    index_to_start = getPidi(listTasks);
 		    
 		    if(index_to_start < 0)
 		    	return;
 		    
 		    if(listTasks.get(previous_index) != null) {
              if(!listTasks.get(previous_index).getStatus().equals("termine"))
 		        listTasks.get(previous_index).setStatus("en attente");
 		    	
 		    }
 		    
 		    listTasks.get(index_to_start).setStatus("En cours");
 		    
 			for(int i=0;i<listTasks.size(); i++)
 			{  
 				if(listTasks.get(i).getStatus() == "en attente")
 				System.out.println(  "\033[0;34m"  + listTasks.get(i).toString() + "\033[0;30m" );
 				else if(listTasks.get(i).getStatus() == "En cours")
 					System.out.println(  "\033[0;32m"  + listTasks.get(i).toString() + "\033[0;30m" );
 				else if(listTasks.get(i).getStatus() == "termine")
 					System.out.println(  "\033[0;31m"  + listTasks.get(i).toString() + "\033[0;30m" );
 				else 
 				System.out.println(listTasks.get(i).toString());	
 			
 			}
 		    
 	//		System.out.println(index_to_start);
 			
 		    nextArrivalTime = NextarrivalTime(listTasks,index_to_start);  
 		
 		     if(nextArrivalTime == -1)
 			  nextArrivalTime = t_cpu + listTasks.get(index_to_start).getD(); 
 		    
 		    if(listTasks.get(index_to_start).getTemps_deb() == -1 )
 				listTasks.get(index_to_start).setTemps_deb(t_cpu);
 			System.out.println("----------------------------------------------------------------------------------------------");
 		  
 		//	 System.out.println("now = " + arr.get(index_to_start).toString());
 			if(listTasks.get(index_to_start).getD() + t_cpu <= nextArrivalTime ) {
 			     
 				t_cpu = t_cpu + listTasks.get(index_to_start).getD();
 				    
 				   listTasks.get(index_to_start).setStatus("termine");
 			    
 				    listTasks.get(index_to_start).setD(0);
 			     	
 			        listTasks.get(index_to_start).setTemps_final(listTasks.get(index_to_start).getD() + t_cpu);  
 			        listTasks.get(index_to_start).setTemps_final(t_cpu);
 			                            
 			
 			}
 			else {
 				listTasks.get(index_to_start).setD(listTasks.get(index_to_start).getD() - (nextArrivalTime - t_cpu));
 			
 			//	System.out.println("Process   " + arr.get(index_to_start).getProcessname() + "From  " +  t_cpu + "To  " + nextArrivalTime);
 				
 				t_cpu = nextArrivalTime;
 				
 			}
 			System.out.println("Temps CPU = " + t_cpu );
 		}
 		
 	}

 	// liste des processus dans un instant donnée
 	public static void  getListProcess(ArrayList<Task> arr, int t_cpu){
           
 		     ArrayList<Task> ArrToReturn = new ArrayList<Task>(); 
 			for(int i=0;i< arr.size(); i++) {
 		       
 		        if(arr.get(i).getTemps_arriv() <= t_cpu && arr.get(i).getD() > 0 && arr.get(i).getStatus().equals("Pas encore déposé")) {
 		              arr.get(i).setStatus("en attente");
 		        }
 		
 			}
 	}
 	
 	//max pidi to execute
 	public static int  getPidi(ArrayList<Task> arr) {
 		  
 		
 		int max = -1; 
 		for(int i = 0 ; i < arr.size(); i++) {
 			if(arr.get(i).getPidi() > max && arr.get(i).getStatus().equals("en attente")) {
 				max = i;
 			}
 		}
 		
 		return max;
 	}

 	//temps d'arrivée le plus proche
 	public static int NextarrivalTime (ArrayList<Task> arr , int index_to_start) {
 	    
 		 
 		int i =0;
 		for(i=0 ; i < arr.size();i++ )
 		{
             if(arr.get(i).getStatus() == "Pas encore arrivé")
 			return arr.get(i).getTemps_arriv();
 		}
 		return -1;
 	}

//Main  

 public static void main(String args[]) throws IOException{  
	 
	 Scanner partie=new Scanner(System.in);
	 System.out.println("********** Bienvenue **********");
	 System.out.println("Choisir la partie que vous souhaitez");
	 System.out.println("1- Commandes Linux");
	 System.out.println("2- Ordonnancement");
	 int c=partie.nextInt();
	 if(c==1) {
		 System.out.println(CYAN+"************************Welcome to SE CMD************************"+RESET);

			Scanner sc = new Scanner(System.in);


			while(true) {
				String curDir = System.getProperty("user.dir");
				System.out.println(curDir+" > ");
				String commande = sc.nextLine();
				//System.out.println(commande);
				String[] separated = commande.split(" ");
				switch(separated[0]) {
				case "aide":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else if(separated.length==3) {
					Redirection(separated);
					} else {
					aide();
					}
					break;
				case "move":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else {
					move(separated[1]);
					}
					break;
				case "create":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else {
					create(separated[1]);
					}
					break;
				case "content":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					} else if(separated.length==2){
						if(separated[1].equals("-s")) {
							sortLs();
						}
						else {
							System.out.println("Commande invalide!");
						}
					}
					else if(separated.length==3) {
						Redirection(separated);
					} else {
						content();
						}
					break;
				case "delete":
					delete(separated[1]);
					break;
	            case "rename":
					rename(separated[1],separated[2]);
					break;
				case "writeOnFile":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else {
						writeOnANewFileWord(separated[1]);
					}
					break;
				case "removeFile":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else {
						removeFile(separated[1]);
					}
					break;
				case "executeFile":
					if (separated.length > 3 || separated.length == 0) {
						System.out.println("Commande invalide!");
					}
					else {
					executeFile(separated[1]);
					}
				
					break;
				case "moveBack":
					moveBack();
					break;
				case "alias":
					alias(separated);
					break;
				default:
					if(newHelp.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else if(separated.length==3) {
						Redirection(separated);
						} else {
						aide();
						}
					}
					else if(newChangeDirectory.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else {
						move(separated[1]);
						}
					}
					else if(newCreate.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else {
						create(separated[1]);
						}
					}
					else if(newList.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						} else if(separated.length==2){
							if(separated[1].equals("-s")) {
								sortLs();
							}
							else {
								System.out.println("Commande invalide!");
							}
						}
						else if(separated.length==3) {
							Redirection(separated);
						} else {
							content();
							}
					}
					else if(newWriteOnFile.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else {
							writeOnANewFileWord(separated[1]);
						}
					}
					else if(newRemoveFile.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else {
							removeFile(separated[1]);
						}
					}
					else if(newExecuteFile.length()!=0) {
						if (separated.length > 3 || separated.length == 0) {
							System.out.println("Commande invalide!");
						}
						else {
						executeFile(separated[1]);
						}
					}
					else if(newPrecedentDirectory.length()!=0) {
						moveBack();
					}
					else {
					
					System.out.println(RED+"Commande invalide!"+RESET);
					}
				}	
			}
	 }else if(c==2) {
		 System.out.println("***** Les algorithmes d'ordonnancement *****");
		 System.out.println("Choisir l'algorithme souhaité: \n 1- FIFO \n 2- SRT\n 3- Tourniquet \n 4- Priorité préemptive");
		 Scanner choix=new Scanner(System.in);
		 int ch=choix.nextInt();
		 if(ch==1)
		 {
			 System.out.println("***** Algorithme d'ordonnancement FIFO (First In First Out) *****");
			 // Process id's  
			 ArrayList<Integer> processes= new ArrayList<Integer>();
			 ArrayList<Integer> execution=new ArrayList<Integer>();
			 ArrayList<Integer> arrivee=new ArrayList<Integer>();
			 Scanner st=new Scanner(System.in);
			 Scanner s=new Scanner(System.in);
			 Scanner arr=new Scanner(System.in);
			 System.out.println("Donner le nombre de processus");
		     int n=st.nextInt();
		     for(int i=1;i<=n;i++) {
		    	 processes.add(i);
		    	 System.out.println("Donner le temps d'exécution du processus "+i);
		    	 execution.add(s.nextInt());
		    	 System.out.println("Donner le temps d'arrivée du processus "+i);
		    	 arrivee.add(arr.nextInt());
		     }
		     st.close();
		     s.close();
		     arr.close();
		 findavgTimeFifo(processes, n, execution, arrivee); 
		 } else if(ch==2) {
	System.out.println("***** Algorithme d'ordonnancement SRT (Short Remining Time) *****");
	  	     
	  	     Scanner st=new Scanner(System.in);
	  	     Scanner exec=new Scanner(System.in);
	  	     Scanner arr=new Scanner(System.in);
	  	     System.out.println("Donner le nombre de processus");
	  	     int n=st.nextInt();
	  	     ArrayList<Process> process=new ArrayList<Process>();
	  	     for(int i=1;i<=n;i++) {
	  	    	 System.out.println("Saisir le temps d'exécution du processus "+i);
	  	    	 Integer texec=exec.nextInt();
	  	    	System.out.println("Saisir le temps d'arrivée du processus "+i);
	  	    	 Integer tarr=arr.nextInt();
	  	    	 process.add(new Process(i,texec,tarr));
	  	     }
	  	     st.close();
	  	     exec.close();
	  	     arr.close();
	  	findavgTimeSrt(process, process.size());
		 }else if(ch==3) {
			 System.out.println("***** Algorithme d'ordonnancement Tourniquet *****");
			   
		     Scanner sss=new Scanner(System.in);
		     Scanner s=new Scanner(System.in);
		     Scanner qt=new Scanner(System.in);
		     System.out.println("Donner le nombre de processus");
		     int n=sss.nextInt();
		     ArrayList<Integer> processes=new ArrayList<Integer>();
		     ArrayList<Integer> execution=new ArrayList<Integer>();
		     for(int i=1;i<=n;i++) {
		    	 processes.add(i);
		    	 System.out.println("Donner le temps d'exécution du processus "+i);
		    	 execution.add(s.nextInt());
		     }
		     System.out.println("Donner le quantum");
		     int quantum=qt.nextInt();
		     findavgTimeTourniquet(processes, n, execution, quantum); 
		     sss.close();
		     s.close();
		     qt.close();
		 }else if(ch==4) {
			 ArrayList<Task> t = new ArrayList<Task>();
				Scanner ss = new Scanner(System.in);
				System.out.println("                           ****** Algorithme d'ordonnancement préemptif basé sur la priorité ******");
				System.out.println("Donner le nombre de processus");
			    
				int n = ss.nextInt();
				
				Scanner s = new Scanner(System.in);
				Scanner d = new Scanner(System.in);
				Scanner p = new Scanner(System.in);
				Scanner a = new Scanner(System.in);
				
				for(int i=0; i< n;i++) { 
					System.out.println("Donner le Nom du processus "+(i+1));
					String nom=s.nextLine();
					System.out.println("Donner le temps d'exécution du processus "+nom);
					int duree=d.nextInt();
					System.out.println("Quelle est la priorité du processus "+nom);
					int priorite=p.nextInt();
					System.out.println("Donner le temps d'arrivée du processus "+nom);
					int arriv=a.nextInt();
					t.add(new Task(duree , priorite , arriv , nom));
					
	      	}
				
				for(int i =0; i< n; i++)
					System.out.println(t.get(i).toString());
					
					
				ss.close();
				s.close();
				d.close();
				p.close();
				a.close();

				long startTime = System.currentTimeMillis();
			     
				for(int i=0; i< 50; i++)
				{
					System.out.print("-");
					
				}

		        preemptif(t);
	       
		        System.out.println("Tableau final");
				for(int i =0; i< n; i++)
				{				
					System.out.println(t.get(i).toString());
				}
				
				
				long endTime = System.currentTimeMillis();
				System.out.println("Took "+(endTime - startTime) + " ms"); 
			 
		 }else{
			 System.out.println("Veuillez choisir un algorithme !");
		 }
		 choix.close();
	 }
	 partie.close();
			}
	 }
	 
	 
	 

 