package hus.cuong.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import hus.cuong.object.Alphabet;
import hus.cuong.object.Transform;
import hus.cuong.object.TransformFunction;

public class WriteAutomata {

	private BufferedWriter bw;
	public WriteAutomata(String filePath) {
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e) {
			System.out.println("Can't open " + filePath);
			return;
		}
	}

	public void open(String filePath){
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e) {
			System.out.println("Can't open " + filePath);
			return;
		}
	}

	public void write(TransformFunction tff, Alphabet alphabet){
		try {
			bw.write(alphabet + "\n");
			bw.write(tff.newStatusInString() + "\n");
			ArrayList<Transform> listTransform = tff.getTransformFunction();
			for (Transform transform : listTransform) {
				bw.write(transform.toString() + "\n");
			}
		} catch (IOException e) {
			System.out.println("Can't write ");
			return;
		}
		close();
	}

	public void close(){
		try {
			bw.close();
		} catch (IOException e) {
			System.err.println("Can't close writer ");
			return;
		}
	}
}
