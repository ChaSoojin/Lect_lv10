package lms_interface;

import models.Subject;

public interface evaluateable {
	public int[] makeTest();
	public int gradingTest(Subject findSub, int[] stuAnswer);
}
