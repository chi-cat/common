package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.Destroyable;
import top.flyfire.common.chainedmode.Handler;
import top.flyfire.common.chainedmode.HandlerChain;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.NullMetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.*;
import top.flyfire.common.reflect.value.Parser;
import top.flyfire.common.reflect.value.ValueParserHolder;

import java.util.*;

/**
 * Created by shyy_work on 2016/9/13.
 */
public final class WrapperFactory {

    private static HandlerChain<Wrapper, PMetaContext> pmetaHandlerChain;

    private static HandlerChain<Wrapper, CMetaContext> cmetaHandlerChain;

    private static ValueParserHolder valueParserHolder;

    static{
        valueParserHolder = ValueParserHolder.getInstance();
        pmetaHandlerChain = HandlerChain.buildChain(new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (List.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[0];
                    return new CollectionWrapper() {

                        @Override
                        public Collection instance() {
                            return new ArrayList<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return metaInfo;
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (Collection.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[0];
                    return new CollectionWrapper() {

                        @Override
                        public Collection instance() {
                            return new HashSet<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return metaInfo;
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (Map.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[1];
                    return new MapWrapper() {
                        @Override
                        public Map instance() {
                            return new HashMap<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return metaInfo;
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                return wrap(data.parameterizedMetaInfo.asClassMetaInfo());
            }
        });
        cmetaHandlerChain = HandlerChain.buildChain(new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (ReflectUtils.isJdkPrimitiveType(rawType)) {
                    return new ValueWrapper() {

                        Parser valueParser = valueParserHolder.apply(rawType);

                        @Override
                        public Object rawValue(Object instance) {
                            return valueParser.parse(instance);
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (List.class.isAssignableFrom(rawType)) {
                    return new CollectionWrapper() {

                        @Override
                        public Collection instance() {
                            return new ArrayList<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return ReflectUtils.getMetaInfo(Object.class);
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (Collection.class.isAssignableFrom(rawType)) {
                    return new CollectionWrapper() {

                        @Override
                        public Collection instance() {
                            return new HashSet<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return ReflectUtils.getMetaInfo(Object.class);
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (Map.class.isAssignableFrom(rawType)) {
                    return new MapWrapper() {
                        @Override
                        public Map instance() {
                            return new HashMap<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo() {
                            return ReflectUtils.getMetaInfo(Object.class);
                        }

                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final ClassMetaInfo classMetaInfo = data.classMetaInfo;
                return new BuildInWrapper() {

                    @Override
                    public Object instance() {
                        return classMetaInfo.newInstance();
                    }

                    @Override
                    public FieldMetaInfo getField(String s) {
                        FieldMetaInfo field = classMetaInfo.getField(s);
                        return field;
                    }

                };
            }
        });
    }

    public final static Wrapper wrap(MetaInfo metaInfo) {
        if (metaInfo instanceof ClassMetaInfo) {
            return wrap((ClassMetaInfo) metaInfo);
        } else if (metaInfo instanceof ParameterizedMetaInfo) {
            return wrap((ParameterizedMetaInfo) metaInfo);
        } else if (metaInfo instanceof WildcardMetaInfo) {
            return wrap((WildcardMetaInfo) metaInfo);
        } else if (metaInfo instanceof VariableMetaInfo) {
            return wrap((VariableMetaInfo) metaInfo);
        } else if (metaInfo instanceof ArrayMetaInfo) {
            return wrap((ArrayMetaInfo) metaInfo);
        } else if(metaInfo instanceof NullMetaInfo){
            return new ValueWrapper() {
                @Override
                public Object rawValue(Object instance) {
                    return null;
                }
            };
        }
        throw new ReflectiveException();
    }


    private static Wrapper wrap(WildcardMetaInfo wildcardMetaInfo) {
        MetaInfo bound;
        if (!MetaInfo.NULL.equals(bound = wildcardMetaInfo.getLowerBound())) {
            return wrap(bound);
        } else if (MetaInfo.NULL.equals(bound = wildcardMetaInfo.getUpperBound())) {
            return wrap(ReflectUtils.getMetaInfo(Object.class));
        } else {
            return wrap(bound);
        }
    }

    private static Wrapper wrap(VariableMetaInfo variableMetaInfo) {
        MetaInfo bound;
        if (MetaInfo.NULL.equals(bound = variableMetaInfo.getBound())) {
            return wrap(ReflectUtils.getMetaInfo(Object.class));
        } else {
            return wrap(bound);
        }
    }

    private static Wrapper wrap(final ArrayMetaInfo arrayMetaInfo) {
        return new ArrayWrapper() {
            @Override
            public Collection instance() {
                return new ArrayList<>();
            }

            @Override
            public MetaInfo getMetaInfo() {
                return arrayMetaInfo.getGenericComponentType();
            }

            @Override
            public Object rawValue(Object instance) {
                return ((List) instance).toArray();
            }
        };
    }

    private static Wrapper wrap(final ParameterizedMetaInfo parameterizedMetaInfo) {
        PMetaContext pMetaContext = new PMetaContext(parameterizedMetaInfo);
        try {
            return pmetaHandlerChain.handling(pMetaContext);
        } finally {
            pMetaContext.destroy();
        }
    }

    private static Wrapper wrap(ClassMetaInfo classMetaInfo) {
        CMetaContext cMetaContext = new CMetaContext(classMetaInfo);
        try {
            return cmetaHandlerChain.handling(cMetaContext);
        } finally {
            cMetaContext.destroy();
        }
    }

    private static class PMetaContext implements Destroyable {

        ParameterizedMetaInfo parameterizedMetaInfo;

        Class rawType;

        public PMetaContext(ParameterizedMetaInfo parameterizedMetaInfo) {
            this.parameterizedMetaInfo = parameterizedMetaInfo;
            ClassMetaInfo classMetaInfo = (ClassMetaInfo) parameterizedMetaInfo.getRawType();
            this.rawType = classMetaInfo.getRawType();
        }

        @Override
        public void destroy() {
            this.parameterizedMetaInfo = null;
            this.rawType = null;
        }
    }

    private static class CMetaContext implements Destroyable {
        ClassMetaInfo classMetaInfo;

        Class rawType;

        public CMetaContext(ClassMetaInfo classMetaInfo) {
            this.classMetaInfo = classMetaInfo;
            this.rawType = classMetaInfo.getRawType();
        }

        @Override
        public void destroy() {
            this.classMetaInfo = null;
            this.rawType = null;
        }
    }

}
