package com.drill.udf.overloading;

import io.netty.buffer.DrillBuf;
import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.holders.VarCharHolder;
import javax.inject.Inject;

@FunctionTemplate(
    name="log",
    scope= FunctionTemplate.FunctionScope.SIMPLE,
    nulls = FunctionTemplate.NullHandling.NULL_IF_NULL
)
public class Log implements DrillSimpleFunc {

  @Param
  VarCharHolder input1;

  @Output
  VarCharHolder out;

  @Inject
  DrillBuf buffer;

  public void setup() {

  }

  public void eval() {
    String inputString = org.apache.drill.exec.expr.fn.impl.StringFunctionHelpers.toStringFromUTF8(input1.start, input1.end, input1.buffer);
    String outputValue = "LOG was overloaded. Input: " + inputString;

    out.buffer = buffer;
    out.start = 0;
    out.end = outputValue.getBytes().length;
    buffer.setBytes(0, outputValue.getBytes());
  }
}