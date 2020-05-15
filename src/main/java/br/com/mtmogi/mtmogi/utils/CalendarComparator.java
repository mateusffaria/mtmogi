package br.com.mtmogi.mtmogi.utils;

import java.util.Calendar;
import java.util.Comparator;

import br.com.mtmogi.mtmogi.model.Salario;

public class CalendarComparator implements Comparator<Salario> { 
	    
	    public int compare(Salario o1, Salario o2) { 
	        
	        Calendar c1 = ( Calendar ) o1.getDataAtualizacao(); 
	        Calendar c2 = ( Calendar ) o2.getDataAtualizacao(); 
	        
	        int flag = 0; 
	        
	        if( c1.getTimeInMillis() > c2.getTimeInMillis() ) { 
	            flag = -1; 
	        } else { 
	            if( c1.getTimeInMillis() < c2.getTimeInMillis() ) { 
	                flag = 1; 
	            } 
	        } 
	        
	        return flag; 
	        
	    } 
	    
} 
	
	

