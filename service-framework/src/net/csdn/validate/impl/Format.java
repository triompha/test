package net.csdn.validate.impl;

import net.csdn.validate.BaseValidateParse;
import net.csdn.validate.ValidateHelper;
import net.csdn.validate.ValidateResult;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BlogInfo: WilliamZhu
 * Date: 12-7-4
 * Time: 下午12:59
 */
public class Format extends BaseValidateParse {
    private static String notice = "{} is invalid";

    @Override
    public void parse(final Object target, final List<ValidateResult> validateResultList) {
        final Class clzz = target.getClass();
        iterateValidateInfo(clzz, ValidateHelper.format, new ValidateIterator() {
            @Override
            public void iterate(String targetFieldName, Field field, Object info) throws Exception {
                String msg = notice;
                if (info instanceof Map) msg = messageWithDefault((Map) info, notice);
                String value = (String) getModelField(clzz, targetFieldName).get(target);
                if (value == null || value.isEmpty()) {
                    validateResultList.add(validateResult(msg, targetFieldName));
                }
                Map format = (Map) info;
                String regex = (String) format.get(ValidateHelper.Format.with);
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                if (!matcher.matches()) {
                    validateResultList.add(validateResult(msg, targetFieldName));
                }


            }
        });
    }
}
