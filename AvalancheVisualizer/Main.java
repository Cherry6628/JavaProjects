package avalanche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Main{
    private static Scanner sc=null;
    private static void displayTitle(){
        System.out.println("\033c");
System.out.println("""


 $$$$$$\\                      $$\\                               $$\\                 
$$  __$$\\                     $$ |                              $$ |                
$$ /  $$ |$$\\    $$\\ $$$$$$\\  $$ | $$$$$$\\  $$$$$$$\\   $$$$$$$\\ $$$$$$$\\   $$$$$$\\  
$$$$$$$$ |\\$$\\  $$  |\\____$$\\ $$ | \\____$$\\ $$  __$$\\ $$  _____|$$  __$$\\ $$  __$$\\ 
$$  __$$ | \\$$\\$$  / $$$$$$$ |$$ | $$$$$$$ |$$ |  $$ |$$ /      $$ |  $$ |$$$$$$$$ |
$$ |  $$ |  \\$$$  / $$  __$$ |$$ |$$  __$$ |$$ |  $$ |$$ |      $$ |  $$ |$$   ____|
$$ |  $$ |   \\$  /  \\$$$$$$$ |$$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$$\\ 
\\__|  \\__|    \\_/    \\_______|\\__| \\_______|\\__|  \\__| \\_______|\\__|  \\__| \\_______|
                                                                                    
                                                                                    
                                                                                    
$$\\    $$\\ $$\\                               $$\\ $$\\                                
$$ |   $$ |\\__|                              $$ |\\__|                               
$$ |   $$ |$$\\  $$$$$$$\\ $$\\   $$\\  $$$$$$\\  $$ |$$\\ $$$$$$$$\\  $$$$$$\\   $$$$$$\\   
\\$$\\  $$  |$$ |$$  _____|$$ |  $$ | \\____$$\\ $$ |$$ |\\____$$  |$$  __$$\\ $$  __$$\\  
 \\$$\\$$  / $$ |\\$$$$$$\\  $$ |  $$ | $$$$$$$ |$$ |$$ |  $$$$ _/ $$$$$$$$ |$$ |  \\__| 
  \\$$$  /  $$ | \\____$$\\ $$ |  $$ |$$  __$$ |$$ |$$ | $$  _/   $$   ____|$$ |       
   \\$  /   $$ |$$$$$$$  |\\$$$$$$  |\\$$$$$$$ |$$ |$$ |$$$$$$$$\\ \\$$$$$$$\\ $$ |       
    \\_/    \\__|\\_______/  \\______/  \\_______|\\__|\\__|\\________| \\_______|\\__|       



""");
    }
    private static boolean exitVerification(){
        System.out.println("Do you sure want to exit ? N/y");
        return getConsent();
    }
    private static boolean getConsent(){
        return sc.nextLine().strip().toLowerCase().equals("y");
    }
    private static String padText(String s, int l){
        return s+(" ".repeat(Math.max(l-s.length(), 0)));
    }
    private static int displayOptions(String title, String[] options, int space, boolean clearScreen){
        int additionalGap = 2;
        int maxDigitSize;
        try{maxDigitSize = (int)Math.floor(Math.log10(options.length-1))+1;}
        catch(Exception e){maxDigitSize=1;}
        int maxLength = title.length();
        for (String s: options){
            int l=s.length();
            if(maxLength<l)maxLength=l+additionalGap+2+maxDigitSize;
        }
        float base = (maxLength-title.length()+maxDigitSize+2)/2.0f;
        int start = (int)Math.floor(base)+1;
        int end = (int)Math.ceil(base)+1;
        if(clearScreen)System.out.println("\033[H\033[2J\n");
        System.out.println("#".repeat(start)+(" ".repeat(space))+title+(" ".repeat(space))+("#".repeat(end)));
        System.out.println("#"+(" ".repeat(space+maxDigitSize+2+maxLength+space))+"#");
        
        for (int i=0; i<options.length; i++){
            String option = options[i];
            System.out.println("#"+(" ".repeat(space))+padText(""+(i+1), maxDigitSize)+". "+(padText(option, maxLength))+(" ".repeat(space))+"#");
        }
        System.out.println("#"+(" ".repeat(space+maxDigitSize+2+maxLength+space))+"#");
        System.out.println("#".repeat(maxLength+2+(2*space)+maxDigitSize+2));
        System.out.println("");
        int choice;
        do{
            System.out.print("Enter Your Choice >> ");
            try{
                choice = sc.nextInt();
            } catch(Exception e){
                choice=-1;
            } finally{
                sc.nextLine();
            }
            if (choice>0 && choice <= options.length)return choice;
            
            System.out.println("Invalid Option. Try Again.\n");
        } while(true);

    }
    
    private static int displayOptions(String title, String[] options, int space){
        return displayOptions(title, options, space, true);
    }
    private static String fileContent(String file){
        File f = new File(file);
        System.out.print("Trying to Access "+f.getAbsolutePath());
        if(!f.exists()){System.out.println("");return null;}
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String result="";
            String line = br.readLine();
            while(line!=null){
                result+=(line+"\n");
                line=br.readLine();
            }


            System.out.print("\r"+(" ".repeat(100))+"\r");
            fr.close();
            br.close();
            return result.substring(0, result.length()-1);
        } catch(Exception e){
            System.out.println("");
            return null;
        }

    }
    private static int intInput(String message){
        System.out.print(message);
        int c;
        while(true){
            try{c = sc.nextInt();}
            catch(Exception e){System.out.println("Invalid Input\n");sc.nextLine();continue;}
            sc.nextLine();
            return c;
        }
    }
    public static void main(String[]args){
        displayTitle();
        boolean wasNotFirst=false;
        try{
            sc = new Scanner(System.in);
            int option;
            mainLoop: while(true){
                option = displayOptions("Main Menu", new String[]{"Avalanche", "Hashing", "Instructions", "Credits", "Exit"}, 2, wasNotFirst);
                wasNotFirst=true;
                switch (option){
                    case 1:{
                            option = displayOptions("Avalanche", new String[]{
                            "Visualize Avalanche",
                            "What is Avalanche ? ",
                            "Return to Main Menu"
                        }, 2);
                        switch (option){
                            case 3:{
                                break;
                            }

                            case 1:{
                                option=displayOptions(
                                    "Visualize Avalanche",
                                    new String[]{"Visualize Internal Avalanche", "Visualize External Avalanche", "Return"},
                                    2
                                );
                                switch (option){
                                    case 3:{
                                        break;
                                    }
                                    case 2:{
                                        option = displayOptions("External Avalanche Visualization", new String[]{
                                            "Test External Avalanche with Definite Bit Flip",
                                            "Test External Avalanche with Multiple Random Flips",
                                            "Run Full SAC Test",
                                            "Compare Two Strings",
                                            "Exit"
                                        }, 2);
                                        if(option==5)break;

                                        int hash = displayOptions("Select Hash Type", new String[]{
                                            "MD5",
                                            "SHA224",
                                            "SHA256",
                                            "SHA384",
                                            "SHA512",
                                            "Cancel"
                                        }, 2);
                                        if (hash==6)break;
                                        if (option==1){
                                            System.out.print("Enter Base Word to Display Avalanche >> ");
                                            String baseWord = sc.nextLine();
                                            int bitToFlip = intInput("Enter which bit to flip >> ");
                                            if(hash==1)ExternalAvalanche.testMD5ExternalAvalancheWithDefiniteBit(baseWord, bitToFlip); 
                                            else{
                                                int shaBits=0;
                                                if(hash==2)shaBits=224;
                                                else if(hash==3)shaBits=256;
                                                else if(hash==4)shaBits=384;
                                                else if(hash==5)shaBits=512;
                                                ExternalAvalanche.testSHAExternalAvalancheWithDefiniteBit(baseWord, shaBits, bitToFlip);
                                            }
                                        }
                                        else if(option==2){
                                            System.out.print("Enter Base Word to Display Avalanche >> ");
                                            String baseWord = sc.nextLine();
                                            int times = intInput("Enter how many random tests should be done >> ");
                                            System.out.print("Enter whether to print the logs or not [N/y] >> ");
                                            boolean shallPrint = getConsent();
                                            if(hash==1)ExternalAvalanche.runRandomMD5ExternalAvalancheTest(baseWord, times, shallPrint);
                                            else{
                                                int shaBits=0;
                                                if(hash==2)shaBits=224;
                                                else if(hash==3)shaBits=256;
                                                else if(hash==4)shaBits=384;
                                                else if(hash==5)shaBits=512;
                                                ExternalAvalanche.runRandomSHAExternalAvalancheTest(baseWord, times, shaBits, shallPrint);
                                            }
                                        }

                                        else if(option==3){
                                            System.out.println("\033[91mWARNING: SAC Test may take long time based on the input string\033[0m");
                                            System.out.print("Enter Base Word to Display Avalanche >> ");
                                            String baseWord = sc.nextLine();
                                            System.out.print("Enter whether to print the logs or not [N/y] (Logs may be very long) >> ");
                                            boolean shallPrint = getConsent();
                                            if(hash==1)ExternalAvalanche.runSACTestWithMD5(baseWord, shallPrint);
                                            else{
                                                int shaBits=0;
                                                if(hash==2)shaBits=224;
                                                else if(hash==3)shaBits=256;
                                                else if(hash==4)shaBits=384;
                                                else if(hash==5)shaBits=512;
                                                ExternalAvalanche.runSACTestWithSHA(baseWord, shaBits, shallPrint);
                                            }
                                        }
                                        else if (option==4){
                                            System.out.print("Enter First Word >> ");
                                            String baseWord = sc.nextLine();
                                            System.out.print("Enter Second Word >> ");
                                            String baseWord2 = sc.nextLine();
                                            if(hash==1)ExternalAvalanche.compareStringMD5Hashes(baseWord, baseWord2);
                                            else{
                                                int shaBits=0;
                                                if(hash==2)shaBits=224;
                                                else if(hash==3)shaBits=256;
                                                else if(hash==4)shaBits=384;
                                                else if(hash==5)shaBits=512;
                                                ExternalAvalanche.compareStringSHAHashes(baseWord, baseWord2, shaBits);
                                            }
                                            
                                        }
                                        System.out.print("\nPress Enter to Continue ... ");
                                        sc.nextLine();

                                        break;
                                    }
                                    case 1:{
                                        option = displayOptions("Internal Avalanche Visualization", new String[]{
                                            "Show Full Hex History",
                                            "Show Average Avalanche Per Round",
                                            "Show Average Avalanche Per Round (Heat Map)",
                                            "Show Average Avalanche Per Round (Table)",
                                            "Show Per-Variable Diffusion",
                                            "Show Cumultative Avalanche Distribution Per Round",
                                            "Show Cumultative Avalanche Distribution Per Round (Heat Map)",
                                            "Show Cumultative Avalanche Distribution Per Round (Table)",
                                            "Return"

                                        }, 2);
                                        if (option<=0||option>8){
                                            System.out.println("Invalid Input.");
                                            break;
                                        }
                                        if (option==9)break;
                                        int hash = displayOptions("Select Hash Type", new String[]{
                                            "MD5",
                                            "SHA224",
                                            "SHA256",
                                            "SHA384",
                                            "SHA512",
                                            "Cancel"
                                        }, 2);
                                        if (hash==6)break;
                                        System.out.print("Enter Base Word to Track History >> ");
                                        String baseWord = sc.nextLine();
                                        InternalWorkingVariableHistory result=null;
                                        if (hash==1){
                                            result = MD5.hash(baseWord);
                                        } else if (hash==2){
                                            result = SHA.hash(baseWord, 224);
                                        } else if (hash==3){
                                            result = SHA.hash(baseWord, 256);
                                        } else if (hash==4){
                                            result = SHA.hash(baseWord, 384);
                                        } else if (hash==5){
                                            result = SHA.hash(baseWord, 512);
                                        }
                                        if (result==null){
                                            System.out.println("Internal Error Occurred.");
                                            break;
                                        }
                                        System.out.println("Input Word  : "+baseWord);
                                        System.out.println("Hash        : "+result.result());
                                        System.out.println("");
                                        int bar = 50;
                                        int dataPerRow = 10;
                                        if(option==1)InternalAvalanche.hexHistory(result.history());
                                        else if(option==2)InternalAvalanche.avgAvalanche(result.history());
                                        else if(option==3)InternalAvalanche.avgAvalancheHeatMap(result.history(), bar);
                                        else if(option==4)InternalAvalanche.avgAvalancheTabular(result.history(), dataPerRow);
                                        else if(option==5)InternalAvalanche.perVariableDiffusion(result.history(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
                                        else if(option==6)InternalAvalanche.cumultativeAvalanche(result.history());
                                        else if(option==7)InternalAvalanche.cumultativeAvalancheHeatMap(result.history(), bar);
                                        else if(option==8)InternalAvalanche.cumultativeAvalancheTabular(result.history(), dataPerRow);
                                        System.out.print("\nPress Enter to Continue ... ");
                                        sc.nextLine();
                                        break;
                                    }
                                    default:{System.out.println("Invalid Option");}                
                                }
                                break;
                            }
                            case 2:{
                                System.out.println("""
\nIn cryptography, the avalanche effect is a desirable 
property where a minor change to the input (like a 
single bit in the plaintext or key) causes a significant, 
often drastic, change in the output (the ciphertext). 

This effect is crucial for strong encryption algorithms 
like block ciphers and cryptographic hash functions, as 
it prevents attackers from making predictions about the 
input by analyzing the output. A lack of a strong 
avalanche effect indicates poor randomization and a 
vulnerability that could lead to the algorithm being 
broken. 

(Press Enter To Continue)  
                                """);
                                sc.nextLine();
                                System.out.println("""

Internal Avalanche: 
Every Hashing Algorithm has several working variable 
which mutates throughout the process.
The avalanche effect is achieved through the iterative 
processing and complex mixing (confusion and diffusion) 
of these internal variables within the algorithm's 
rounds.

(Press Enter To Continue)  
                                """);
                                sc.nextLine();
                                System.out.println("""
External Avalanche: 
External Avalanche is nothing but the avalanche effect 
observed in the output when a bit is flipped.
This plays important role in security consideration.

(Press Enter To Continue)      
                                """);
                                sc.nextLine();

                                break;
                            }


                            default:{System.out.println("Invalid Input");}
                        }
 
                    break;}
                    case 2:{
                        option = displayOptions("Hashing", new String[]{"Hash a Text", "Hash a File", "Return to Main Menu"}, 2);
                        if(option==3){
                            break;
                        }
                        else if (option==1){
                            int hash = displayOptions("Select Hash Type", new String[]{
                                "MD5",
                                "SHA224",
                                "SHA256",
                                "SHA384",
                                "SHA512",
                                "Cancel"
                            }, 2);
                            if (hash==6)break;
                            System.out.print("Enter Base Word  >> ");
                            String baseWord = sc.nextLine();
                            InternalWorkingVariableHistory result=null;
                            if (hash==1){
                                result = MD5.hash(baseWord);
                            } else if (hash==2){
                                result = SHA.hash(baseWord, 224);
                            } else if (hash==3){
                                result = SHA.hash(baseWord, 256);
                            } else if (hash==4){
                                result = SHA.hash(baseWord, 384);
                            } else if (hash==5){
                                result = SHA.hash(baseWord, 512);
                            }
                            if (result==null){
                                System.out.println("Internal Error Occurred.");
                                break;
                            }
                            System.out.println("Input Word  : "+baseWord);
                            System.out.println("Hash        : "+result.result());
                            System.out.println("");
                            System.out.print("\nPress Enter to Continue ... ");
                            sc.nextLine();
                        }
                        else if (option==2){
                            int hash = displayOptions("Select Hash Type", new String[]{
                                "MD5",
                                "SHA224",
                                "SHA256",
                                "SHA384",
                                "SHA512",
                                "Cancel"
                            }, 2);
                            if (hash==6)break;
                            System.out.print("Enter File Name  >> ");
                            String file = sc.nextLine();
                            String baseWord = fileContent(file);
                            if (baseWord==null){
                                System.out.println("Unable to Read the File /  No File Exists");
                                System.out.print("\nPress Enter to Continue ... ");
                                sc.nextLine();
                                break;
                            }

                            InternalWorkingVariableHistory result=null;
                            if (hash==1){
                                result = MD5.hash(baseWord);
                            } else if (hash==2){
                                result = SHA.hash(baseWord, 224);
                            } else if (hash==3){
                                result = SHA.hash(baseWord, 256);
                            } else if (hash==4){
                                result = SHA.hash(baseWord, 384);
                            } else if (hash==5){
                                result = SHA.hash(baseWord, 512);
                            }
                            if (result==null){
                                System.out.println("Internal Error Occurred.");
                                break;
                            }
                            System.out.println("Input File  : "+file);
                            System.out.println("Hash        : "+result.result());
                            System.out.println("");
                            System.out.print("\nPress Enter to Continue ... ");
                            sc.nextLine();
                        }
                    break;}
                    case 3:{
                        System.out.println("""
This Tool is an educational tool that helps tracking 
hash generation throughout the rounds as well as in 
hashing words and files.

You can select 'whether to hash files or simple texts'.
You can also track Avalanche.  To Know about Avalanche,
Go to "[1] Avalanche > [2] What is Avalanche ?"
""");
                        System.out.print("\nPress Enter to Continue ... ");
                        sc.nextLine();
                        break;
                    }
                    case 4:{
                        System.out.println("Created and Developed by Sanjeevi...");
                        System.out.print("\nPress Enter to Continue ... ");
                        sc.nextLine();
                        break;
                    }
                    case 5:{if (exitVerification()) break mainLoop; break;}
                    default:{System.out.println("Invalid Input");}
                }
            }


        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(sc!=null)sc.close();
            System.out.println("Thank You...");
        }
        
    }
}
