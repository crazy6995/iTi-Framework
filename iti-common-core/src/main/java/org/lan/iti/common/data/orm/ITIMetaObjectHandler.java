/*
 *
 *  * Copyright (c) [2019-2020] [NorthLan](lan6995@gmail.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.lan.iti.common.data.orm;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author NorthLan
 * @date 2020-03-20
 * @url https://noahlan.com
 */
@Slf4j
public abstract class ITIMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(getCreateTimeFieldName(), metaObject);
        if (createTime == null) {
            setFieldValByName(getCreateTimeFieldName(), LocalDateTime.now(), metaObject);
        }
        Object createBy = getFieldValByName(getCreateByFieldName(), metaObject);
        if (createBy == null) {
            try {
                setFieldValByName(getCreateByFieldName(), innerUserId(), metaObject);
            } catch (Throwable e) {
                log.warn("createBy设定异常，请检查MetaObjectHandler相关代码", e);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(getUpdateTimeFieldName(), LocalDateTime.now(), metaObject);
        try {
            setFieldValByName(getUpdateByFieldName(), innerUserId(), metaObject);
        } catch (Throwable e) {
            log.warn("updateBy设定异常，请检查MetaObjectHandler相关代码", e);
        }
    }

    /**
     * 获取创建时间字段名 (若业务与此不一致,重写此方法)
     */
    protected String getCreateTimeFieldName() {
        return "createTime";
    }

    /**
     * 获取创建人字段名 (若业务与此不一致,重写此方法)
     */
    protected String getCreateByFieldName() {
        return "createBy";
    }

    /**
     * 获取更新时间字段名 (若业务与此不一致,重写此方法)
     */
    protected String getUpdateTimeFieldName() {
        return "updateTime";
    }

    /**
     * 获取更新人字段名 (若业务与此不一致,重写此方法)
     */
    protected String getUpdateByFieldName() {
        return "updateBy";
    }

    /**
     * 获取用户唯一ID
     */
    private Object innerUserId() {
        Object ret = getUserId();
        if (ret == null) {
            return -1L;
        }
        return ret;
    }

    /**
     * 需扩展此方法，读取用户信息 createBy
     */
    abstract protected Object getUserId();
}
