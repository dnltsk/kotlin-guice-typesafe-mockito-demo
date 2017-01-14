package org.dnltsk.kotlinguicetypesafemockitodemo

import com.google.inject.Binder
import com.google.inject.Module


open class DemoModule : Module {

    override fun configure(binder: Binder?) {
        //nothing to do here because every class is simply implemented once
    }


}

