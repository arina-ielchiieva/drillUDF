package com.drill.udf.overloading;

import io.netty.buffer.DrillBuf;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.holders.VarCharHolder;
import javax.inject.Inject;

@FunctionTemplate(
    name="abs",
    scope= FunctionTemplate.FunctionScope.SIMPLE,
    nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class Abs implements DrillSimpleFunc {

  @Param
  VarCharHolder input1;

  @Param
  VarCharHolder input2;

  @Output
  VarCharHolder out;

  @Inject
  DrillBuf buffer;

  public void setup() {

  }

  public void eval() {
    String inputString1 = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.toStringFromUTF8(input1.start, input1.end, input1.buffer);
    String inputString2 = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.toStringFromUTF8(input2.start, input2.end, input2.buffer);
    String outputValue = String.format("ABS was overloaded. Input: %s, %s", inputString1, inputString2);

    out.buffer = buffer;
    out.start = 0;
    out.end = outputValue.getBytes().length;
    buffer.setBytes(0, outputValue.getBytes());
  }
}