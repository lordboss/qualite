package com.sid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *projet sid et qualité logiciel
 *
 */
public class App 
{
//methode main
    public static void main( String[] args )
    {
	Logger logger = Logger.getLogger("logger");

	    int k=1;
	    //les document pour stocké les structures html
		Document doc, doc2; 
		for (int i = 1; i <= 15; i++) {
			try { 
				doc = Jsoup
						.connect(
								"http://patft.uspto.gov/netacgi/nph-Parser?Sect1=PTO2&Sect2=HITOFF&u=%2Fnetahtml%2FPTO%2Fsearch-adv.htm&r=0&f=S&l=50&d=PTXT&OS=TTL%2Fgreen+OR+TTL%2Fcomputing&RS=TTL%2Fgreen+OR+TTL%2Fcomputing&Query=TTL%2Fgreen+OR+TTL%2Fcomputing&TD=6976&Srch1=green.TI.&Srch2=computing.TI.&Conj1=OR&NextList"
										+ i + "=50+Hits").get();
				// prendre l'url  de chaque article						
				Elements links = doc
						.select("tbody td:eq(1)[valign=top] a[href]");
			    // iterer sur les liens
				for (Element link : links) {
				    // cree un fichier pour stocker les resultas
					PrintWriter fichier; 
					try {
					    // creation de dossier des resultats
						File fb = new File("result"); 
						fb.mkdirs();
						fichier = new PrintWriter(new FileWriter(
								"result./article" + (k++) + ".html"));
						try {
							String url = link.attr("href");
							// connecter au site de l'article
							doc2 = Jsoup
									.connect("http://patft.uspto.gov" + url)
									.get();
							Elements lin = doc2.select("body");
							// id de contenu de l'article
							fichier.print(lin.clone());
							// copier tous le contenu en html
						} catch (IOException e) {
                        logger.log(Level.INFO, "exception");
						}
						
						fichier.close();
					} catch (IOException e) {			
                    logger.log(Level.INFO, "exception");
					}
				}
			} catch (IOException e) {
               logger.log(Level.INFO, "exception");
			}
		}
     
    }
}
