package org.bioinfo.infrared.funcannot;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.bioinfo.infrared.core.common.FeatureList;
import org.bioinfo.infrared.core.funcannot.AnnotationItem;
import org.bioinfo.infrared.core.funcannot.AnnotationUtils;
import org.junit.Test;

public class AnnotationUtilsTest {

	public void testReadAnnotationFile() {
		System.out.println("AnnotatioUtils:  Test - 1");
		try {
			FeatureList<AnnotationItem> list1 = AnnotationUtils.readAnnotationFileDep("/mnt/commons/test/infrared/annotation/extended_annot.txt");
			FeatureList<AnnotationItem> list2 = AnnotationUtils.readAnnotationFileDep("/mnt/commons/test/infrared/annotation/compact_annot.txt");
			System.out.println(list1.toString());
			System.out.println(list2.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	public void testReadExtendedFormatFile() {
		System.out.println("AnnotatioUtils:  Test - 2");
		try {
			FeatureList<AnnotationItem> list = AnnotationUtils.readExtendedFormatFile("/mnt/commons/test/infrared/annotation/extended_annot.txt");
			System.out.println(list.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testReadCompactFormatFile() {
		System.out.println("AnnotatioUtils:  Test - 3");
		try {
			FeatureList<AnnotationItem> list1 = AnnotationUtils.readAnnotationFile("/mnt/commons/test/infrared/annotation/compact_annot.txt");
			FeatureList<AnnotationItem> list2 = AnnotationUtils.readAnnotationFile("/mnt/commons/test/infrared/annotation/extended_annot.txt");
			System.out.println(list1.toString());
			System.out.println(list2.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testToExtendedFormatList() {
		System.out.println("AnnotatioUtils:  Test - 4");
		try {
			FeatureList<AnnotationItem> list = AnnotationUtils.readExtendedFormatFile("/mnt/commons/test/infrared/annotation/extended_annot.txt");
			List<String> stringList = AnnotationUtils.toExtendedFormatList(list);
			System.out.println(stringList.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testToCompactFormatList() {
		System.out.println("AnnotatioUtils:  Test - 5");
		try {
			FeatureList<AnnotationItem> list = AnnotationUtils.readExtendedFormatFile("/mnt/commons/test/infrared/annotation/extended_annot.txt");
			List<String> stringList = AnnotationUtils.toCompactFormatList(list);
			System.out.println(stringList.toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
