///*
// *
// *  * Copyright (c) [2019-2021] [NorthLan](lan6995@gmail.com)
// *  *
// *  * Licensed under the Apache License, Version 2.0 (the "License");
// *  * you may not use this file except in compliance with the License.
// *  * You may obtain a copy of the License at
// *  *
// *  *     http://www.apache.org/licenses/LICENSE-2.0
// *  *
// *  * Unless required by applicable law or agreed to in writing, software
// *  * distributed under the License is distributed on an "AS IS" BASIS,
// *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  * See the License for the specific language governing permissions and
// *  * limitations under the License.
// *
// */
//
//package org.lan.iti.common.core.spi.support;
//
//import org.lan.iti.common.core.spi.annotation.Activate;
//import org.lan.iti.common.core.spi.annotation.SPI;
//
//import java.util.Comparator;
//
///**
// * @author NorthLan
// * @date 2021-07-07
// * @url https://noahlan.com
// */
//public class WrapperComparator implements Comparator<Object> {
//    public static final Comparator<Object> COMPARATOR = new WrapperComparator();
//
//    @Override
//    public int compare(Object o1, Object o2) {
//        if (o1 == null && o2 == null) {
//            return 0;
//        }
//        if (o1 == null) {
//            return -1;
//        }
//        if (o2 == null) {
//            return 1;
//        }
//        if (o1.equals(o2)) {
//            return 0;
//        }
//
//        Class clazz1 = (Class) o1;
//        Class clazz2 = (Class) o2;
//
//        Class<?> inf = findSpi(clazz1);
//
//        OrderInfo a1 = parseOrder(clazz1);
//        OrderInfo a2 = parseOrder(clazz2);
//
//        int n1 = a1 == null ? 0 : a1.order;
//        int n2 = a2 == null ? 0 : a2.order;
//        // never return 0 even if n1 equals n2, otherwise, o1 and o2 will override each other in collection like HashSet
//        return n1 > n2 ? 1 : -1;
//    }
//
//    private Class<?> findSpi(Class clazz) {
//        if (clazz.getInterfaces().length == 0) {
//            return null;
//        }
//
//        for (Class<?> intf : clazz.getInterfaces()) {
//            if (intf.isAnnotationPresent(SPI.class)) {
//                return intf;
//            } else {
//                Class result = findSpi(intf);
//                if (result != null) {
//                    return result;
//                }
//            }
//        }
//
//        return null;
//    }
//
//    private OrderInfo parseOrder(Class<?> clazz) {
//        OrderInfo info = new OrderInfo();
//        if (clazz.isAnnotationPresent(Activate.class)) {
//            Activate activate = clazz.getAnnotation(Activate.class);
//            info.order = activate.order();
//        }
//        return info;
//    }
//
//    private static class OrderInfo {
//        private int order;
//    }
//}
