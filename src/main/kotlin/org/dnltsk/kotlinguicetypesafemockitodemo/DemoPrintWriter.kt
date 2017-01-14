package org.dnltsk.kotlinguicetypesafemockitodemo

import com.google.inject.Singleton
import java.io.PrintWriter

/**
 * for ordinary output.
 * autoflush set true
 */
@Singleton
class DemoPrintWriter : PrintWriter(System.out, true){
}