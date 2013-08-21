package net.csdn.validate.impl;

import net.csdn.common.logging.CSLogger;
import net.csdn.common.logging.Loggers;
import net.csdn.validate.BaseValidateParse;
import net.csdn.validate.ValidateResult;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static net.csdn.validate.ValidateHelper.presence;

/**
 * BlogInfo: WilliamZhu
 * Date: 12-7-3
 * Time: 下午6:22
 */
public class Presence extends BaseValidateParse {
    private CSLogger logger = Loggers.getLogger(getClass());
    private static String notice = "{} should not null or empty";

    public void parse(final Object target, final List<ValidateResult> validateResultList) {
        final Class clzz = target.getClass();
        iterateValidateInfo(clzz, presence, new ValidateIterator() {
            @Override
            public void iterate(String targetFieldName, Field field, Object info) throws Exception {
                String msg = notice;
                if (info instanceof Map) msg = messageWithDefault((Map) info, notice);
                Object value = getModelField(clzz, targetFieldName).get(target);
                if (value instanceof String) {
                    if (StringUtils.isEmpty((String) value)) {
                        validateResultList.add(validateResult(msg, targetFieldName));
                    }
                } else {
                    if (value == null) {
                        validateResultList.add(validateResult(msg, targetFieldName));
                    }
                }
            }
        });
    }


}
