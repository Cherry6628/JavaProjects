package avalanche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Main{
    private static Scanner sc=null;
    private static void displayTitle(){
        System.out.println("\033c");

// System.out.println("""
//
//  ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░ 
// ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        
// ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        
// ░▒▓████████▓▒░░▒▓█▓▒▒▓█▓▒░░▒▓████████▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓██████▓▒░   
// ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▓█▓▒░ ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        
// ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▓█▓▒░ ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        
// ░▒▓█▓▒░░▒▓█▓▒░  ░▒▓██▓▒░  ░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░ 
//                                                                                                                       
//                                                                                                                       
//                 ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓███████▓▒░   
//                 ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░  
//                  ░▒▓█▓▒▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░    ░▒▓██▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░  
//                  ░▒▓█▓▒▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░      ░▒▓█▓▒░  ░▒▓██▓▒░  ░▒▓██████▓▒░ ░▒▓███████▓▒░   
//                   ░▒▓█▓▓█▓▒░ ░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓██▓▒░    ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░  
//                   ░▒▓█▓▓█▓▒░ ░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░  
//                    ░▒▓██▓▒░  ░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░  
//                                                                                                                       
//                                                                                                                       
//
// """);
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
        // System.out.println(start+" "+end);
        if(clearScreen)System.out.println("\033[H\033[2J\n");
        System.out.println("#".repeat(start)+(" ".repeat(space))+title+(" ".repeat(space))+("#".repeat(end)));
        System.out.println("#"+(" ".repeat(space+maxDigitSize+2+maxLength+space))+"#");
        
        // for (String option: options){
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
                                        System.out.println("Not Implemented yet"); /////////////////////////////////////////////////////////////////////////////////////
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
                            // System.out.println(ba);

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


    // public static void main(String[] args){

    //     // System.out.println(Arrays.toString(Helper.encodeToUTF8("adfad\u0000")));
    //     // String s1 = "d131dd02c5e6eec4693d9a0698aff95c2fcab58712467eab4004583eb8fb7f8955ad340609f4b30283e488832571415a085125e8f7cdc99fd91dbdf280373c5bd8823e3156348f5bae6dacd436c919c6dd53e2b487da03fd02396306d248cda0e99f33420f577ee8ce54b67080a80d1ec69821bcb6a8839396f9652b6ff72a70";
    //     // String s2 = "d131dd02c5e6eec4693d9a0698aff95c2fcab50712467eab4004583eb8fb7f8955ad340609f4b30283e4888325f1415a085125e8f7cdc99fd91dbd7280373c5bd8823e3156348f5bae6dacd436c919c6dd53e23487da03fd02396306d248cda0e99f33420f577ee8ce54b67080280d1ec69821bcb6a8839396f965ab6ff72a70";

    //     String s1;

    //     // s1 = "Sanjeevi";
    //     // s1 = "\u0065\u0066\u0067\u0068\u0069\u0070\u0071\u0072\u0073\u0074\u0075\u0076\u0077\u0078\u0079\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\u0090\u0091\u0092\u0093\u0094\u0095\u0096\u0097\u0098\u0099\u0100\u0101\u0102\u0103\u0104\u0105\u0106\u0107\u0108\u0109\u0110\u0111\u0112\u0113\u0114\u0115\u0116\u0117\u0118\u0119\u0120\u0121\u0122\u0123\u0124\u0125\u0126\u0127\u0128\u0129\u0130\u0131\u0132\u0133\u0134\u0135\u0136\u0137\u0138\u0139\u0140\u0141\u0142\u0143\u0144\u0145\u0146\u0147\u0148\u0149\u0150\u0151\u0152\u0153\u0154\u0155\u0156\u0157\u0158\u0159\u0160\u0161\u0162\u0163\u0164\u0165\u0166\u0167\u0168\u0169\u0170\u0171\u0172\u0173\u0174\u0175\u0176\u0177\u0178\u0179\u0180\u0181\u0182\u0183\u0184\u0185\u0186\u0187\u0188\u0189\u0190\u0191\u0192\u0193\u0194\u0195\u0196\u0197\u0198\u0199";
    //     s1 = "Sanjeevi".repeat(123);
    //     // s1 = (new Scanner(System.in)).nextLine();
    //     // System.out.println(s1);
    //     // System.out.println(s2);
    //     // System.out.println(MD5.md5(s1));
    //     // md5("abc");
    //     InternalWorkingVariableHistory res;
    //     // res = MD5.hash(s1);
    //     // res = SHA.hash(s1, 256);
    //     res = MD5.hash(s1);
    //     AvalancheTracker.hexHistory(res.history());
    //     AvalancheTracker.avgAvalanche(res.history());
    //     AvalancheTracker.avgAvalancheHeatMap(res.history(), 50);
    //     AvalancheTracker.avgAvalancheTabular(res.history(), 10);
    //     AvalancheTracker.perVariableDiffusion(res.history(),  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split(""));
    //     AvalancheTracker.cumultativeAvalanche(res.history());
    //     AvalancheTracker.cumultativeAvalancheHeatMap(res.history(), 50);
    //     AvalancheTracker.cumultativeAvalancheTabular(res.history(), 10);


    //     System.out.println("\n"+res);
    //     System.out.println("");


    //     // System.out.println(SHA.hash(s1, 224));
    //     // System.out.println(SHA.hash(s1, 256));
    //     // System.out.println(SHA.hash(s1, 384));
    //     // System.out.println(SHA.hash(s1, 512));

    //     // res = SHA.hash(s1, 224);
    //     // AvalancheTracker.printFullVariableHexHistory(res.history());
    //     // System.out.println(res);
    //     // System.out.println("");

    //     // res = SHA.hash(s1, 256);
    //     // AvalancheTracker.printFullVariableHexHistory(res.history());
    //     // System.out.println(res);
    //     // System.out.println("");

    //     // res = SHA.hash(s1, 384);
    //     // AvalancheTracker.printFullVariableHexHistory(res.history());
    //     // System.out.println(res);
    //     // System.out.println("");

    //     // res = SHA.hash(s1, 512);
    //     // AvalancheTracker.printFullVariableHexHistory(res.history());
    //     // System.out.println(res);
    //     // System.out.println("");



    //     // System.out.println("hELLOHLAKDSFAKLSDJFKSFk\rabcd");
    //     // System.out.println(SHA.hash(s1, 224));
    //     // System.out.println(MD5.hash(s1));

    // }
}
