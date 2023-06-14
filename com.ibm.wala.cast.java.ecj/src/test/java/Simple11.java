/*
 * Copyright (c) 2002 - 2008 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */
public class Simple11 {
    private int value;
    private final float fval = 3.14F;
    private float fval1 = 3.2F;
    public Simple11(int x) {
	value = x;
    }
    public Simple11() {
	this(0);
    }
    public static void doStuff(int N) {
	int prod = 1;
	for(int j=0; j < N; j++)
	    prod *= j;
    }
    public static void main(String[] args) {
	int sum= 0;
	for(int i=0; i < 10; i++) {
	    sum += i;
	}
	Simple11.doStuff(sum);
	Simple11 s = new Simple11();
	s.instanceMethod1();
    }
    public void instanceMethod1() {
	instanceMethod2();
    }
    public float instanceMethod2() {
        return fval * fval1;
    }
}
