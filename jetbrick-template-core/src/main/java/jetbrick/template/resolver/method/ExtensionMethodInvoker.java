/**
 * Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.
 *
 *   Author: Guoqiang Chen
 *    Email: subchen@gmail.com
 *   WebURL: https://github.com/subchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrick.template.resolver.method;

import jetbrick.bean.MethodInfo;
import jetbrick.template.resolver.ParameterUtils;

/**
 * 访问扩展方法 static.method(object, args, ...)
 */
final class ExtensionMethodInvoker implements MethodInvoker {
    private final MethodInfo method;
    private final int length;
    private final boolean isVarArgs;

    public ExtensionMethodInvoker(MethodInfo method) {
        this.method = method;
        this.length = method.getParameterCount();
        this.isVarArgs = method.isVarArgs();
    }

    @Override
    public Object invoke(Object object, Object[] arguments) {
        arguments = ParameterUtils.getActualArguments(arguments, length, isVarArgs, 1);
        arguments[0] = object; // 第一个参数

        return method.invoke(object, arguments);
    }

    @Override
    public boolean isVoidResult() {
        return method.getReturnType() == Void.TYPE;
    }
}