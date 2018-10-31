package edu.sbcc.cs107;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kevin Griffith
 * CS 107: Disassembler Project
 * 
 * This code implements the disassembler as well as pulling apart the Hex file. The hex file format
 * is documented at http://www.keil.com/support/docs/1584/
 */
public class Disassembler {

	/**
	 * Extracts the register operand from a halfword.
	 * 
	 * The register operand (e.g. r0) is used by many mnemonics and is embedded in the data halfword.
	 * It position is specified by the least significant bit and most significant bit. This value is
	 * extracted and concatenated with "r" to give us the desired register.
	 * 
	 * @param hw Halfword that contains the machine code data.
	 * @param lsBitPosition Encoded register value (LSB)
	 * @param msBitPosition Encoded register value (MSB)
	 * @return Register field designation (e.g. r1)
	 */
	public String getRegister(Halfword hw, int lsBitPosition, int msBitPosition) {
		/* Your code here */
		int data = hw.getData();
		String dataToString = String.format("%04X", data);
		data = Integer.parseInt(dataToString,16); 
		String finaldata = Integer.toBinaryString(data);
		finaldata = StringUtils.leftPad(finaldata, 16, "0");
		//System.out.print(finaldata+ "\n");
		finaldata = finaldata.substring(finaldata.length()- msBitPosition-1, finaldata.length()-lsBitPosition);
		int output = Integer.parseInt(finaldata,2);
		String outputString = String.valueOf(output);
		//System.out.print (finaldata);
		
		return "r" + outputString; 
	}
	
	/**
	 * Extracts the immediate operand from a halfword.
	 * 
	 * Same as the getRegister function but returns the embedded immediate value (e.g. #4).
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	 * @param hw Halfword that contains the machine code data.
	 * @param lsBitPosition Encoded immediate value (LSB)
	 * @param msBitPosition Encoded immediate value (MSB)
	 * @return Immediate field designation (e.g. #12)
	 */
	public String getImmediate(Halfword hw, int lsBitPosition, int msBitPosition) {
		/* Your code here */
		int data = hw.getData();
		String dataToString = String.format("%04X", data);
		data = Integer.parseInt(dataToString,16); 
		String finaldata = Integer.toBinaryString(data);
		finaldata = StringUtils.leftPad(finaldata, 16, "0");
		//System.out.print (finaldata + "\n");
		finaldata = finaldata.substring(finaldata.length()- msBitPosition-1, finaldata.length() - lsBitPosition);
		int output = Integer.parseInt(finaldata,2);
		String outputString = String.valueOf(output);
		//System.out.print (finaldata + "\n");
		
		return "#" + outputString; 
	}

	/**
	 * Returns a formatted string consisting of the Mnemonic and Operands for the given halfword.
	 * 
	 * The halfword is decoded into its corresponding mnemonic and any optional operands. The return
	 * value is a formatted string with an 8 character wide field for the mnemonic (left justified) a
	 * single space and then any operands.
	 * 
	 * @param hw Halfword that contains the machine code data.
	 * @return Formatted string containing the mnemonic and any operands.
	 */
	public String dissassembleToString(Halfword hw) {
		/* Your code here */
		int data = hw.getData();
		String dataToString = String.format("%04X", data);
		data = Integer.parseInt(dataToString,16); 
		String finaldata = Integer.toBinaryString(data);
		finaldata = StringUtils.leftPad(finaldata, 16, "0");
		//System.out.print(finaldata + "\n");
		String finaldata1 = finaldata.substring(0,5);
		
		//System.out.print(finaldata1 + "\n");
		String finaldata2 = finaldata.substring(0,7);
		
		//System.out.print(finaldata2 + "\n");
		String register1="", register2="", register3="", imregister="", output = "";
		switch (finaldata1) {
		
		case "00011":
			register1 = getRegister(hw, 0, 2);
			register2 = getRegister(hw, 3, 5);
			imregister = getImmediate(hw, 6, 9);
			output =  String.format("ADDS     %s, %s, %s", register1,register2,imregister);
			
			break;		
		case "01010":
			register1 = getRegister(hw, 0, 2);
			register2 = getRegister(hw, 3, 5);
			register3 = getRegister(hw, 6, 8);
			output =  String.format("LDRSB    %s, [%s, %s]", register1,register2,register3);			
			break;
		case "00100":
			register1 = getRegister(hw,8, 10);
			imregister = getImmediate(hw, 0, 8);
			output =  String.format("MOVS     %s, %s", register1,imregister);
			
			break;
		case "00000":
			register1 = getRegister(hw, 0, 2);
			register2 = getRegister(hw, 3, 5);
			output =  String.format("MOVS     %s, %s", register1,register2);
			
			break;
		case "10111":
			register1 = getRegister(hw, 0, 3);
			register2 = getRegister(hw, 3, 5);
			output =  String.format("REV      %s, %s", register1,register2);
			
			break;
		case "11100":
			output = String.format("B        .", register1,register2);
			break;
		}
		//System.out.print(output);
		
		switch(finaldata2) {//used for where more characters are needed to distinguish
		case "0100000":
			register1 = getRegister(hw, 0, 3);
			register2 = getRegister(hw, 3, 5);
			output =  String.format("ADCS     %s, %s", register1,register2);
		
			break;
		case "0100001":
			register1 = getRegister(hw, 0, 3);
			register2 = getRegister(hw, 3, 5);
			output =  String.format("CMN      %s, %s", register1,register2);
			
			break;
		
		}
		//System.out.print(output+ "\n");
		return output;
	
		
		
	}
	
}
