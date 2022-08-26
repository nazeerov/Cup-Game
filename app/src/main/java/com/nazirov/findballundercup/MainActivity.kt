package com.nazirov.findballundercup

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import com.nazirov.findballundercup.databinding.ActivityMainBinding
import io.michaelrocks.paranoid.Obfuscate


@Obfuscate
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var numberOfTrue = 0
    private var numberOfFalse = 0

    private var cupHasBall = 1
    private var isContinueGame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cupHasBall = (1..3).shuffled().last()

        binding.laDown.visibility = View.VISIBLE
        binding.laDownCup1.visibility = View.INVISIBLE
        binding.laDownCup2.visibility = View.INVISIBLE
        binding.laDownCup3.visibility = View.INVISIBLE
        initViews()
    }

    private fun initViews() {

        binding.apply {
            tvPlay.setOnClickListener {
                startGame(cupHasBall)
                tvPlay.isEnabled = false
                binding.laDown.visibility = View.INVISIBLE
                unEnabledCup()
            }

            ivCup1.setOnClickListener {
                isContinueGame = false
                if (cupHasBall == 1) {
                    showBall(ivCup1, true)
                } else {
                    showBall(ivCup1, false)
                }
            }

            ivCup2.setOnClickListener {
                isContinueGame = false
                if (cupHasBall == 2) {
                    showBall(ivCup2, true)

                } else {
                    showBall(ivCup2, false)
                }
            }

            ivCup3.setOnClickListener {
                isContinueGame = false
                if (cupHasBall == 3) {
                    showBall(ivCup3, true)

                } else {
                    showBall(ivCup3, false)
                }
            }
            tvExit.setOnClickListener {
                finish()
            }
        }
    }

    private fun unEnabledCup() {
        binding.apply {
            ivCup1.isEnabled = false
            ivCup2.isEnabled = false
            ivCup3.isEnabled = false
        }
    }

    private fun showResult(result: Boolean) {
        binding.apply {
            val timer = object : CountDownTimer(1000, 1000) {

                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    binding.laDownCup1.visibility = View.INVISIBLE
                    binding.laDownCup2.visibility = View.INVISIBLE
                    binding.laDownCup3.visibility = View.INVISIBLE
                    if (result) {
                        tvResult.text = "Win"
                        tvTrue.text = "True : ${++numberOfTrue}"
                    } else {
                        tvResult.text = "Loose"
                        tvFalse.text = "False : ${++numberOfFalse}"
                    }
                    tvResult.visibility = View.VISIBLE
                }

                override fun onFinish() {
                    tvResult.visibility = View.INVISIBLE
                    tvPlay.isEnabled = true
                    laDown.visibility = View.VISIBLE
                }
            }
            timer.start()
        }
    }

    private fun startGame(cupHasBall: Int) {
        isContinueGame = true
        showBall(chooseIV(cupHasBall)!!, false)
    }

    private fun chooseIV(cupHasBall: Int): ImageView? {
        binding.apply {
            when (cupHasBall) {
                1 -> {
                    ivBall1.visibility = View.VISIBLE
                    ivBall2.visibility = View.INVISIBLE
                    ivBall3.visibility = View.INVISIBLE
                    return ivCup1
                }

                2 -> {
                    ivBall1.visibility = View.INVISIBLE
                    ivBall2.visibility = View.VISIBLE
                    ivBall3.visibility = View.INVISIBLE
                    return ivCup2
                }

                3 -> {
                    ivBall1.visibility = View.INVISIBLE
                    ivBall2.visibility = View.INVISIBLE
                    ivBall3.visibility = View.VISIBLE
                    return ivCup3
                }
            }
        }
        return null
    }

    private fun showBall(imageCup: ImageView, result: Boolean) {
        unEnabledCup()
        ObjectAnimator.ofFloat(imageCup, "translationY", -150F).apply {
            duration = 100
            start()
        }.addListener({
            Thread.sleep(500)
            ObjectAnimator.ofFloat(imageCup, "translationY", 0F).apply {
                duration = 100
                doOnEnd {
                    Thread.sleep(100)
                    if (isContinueGame) {
                        cupHasBall = (1..3).shuffled().last()
                        chooseIV(cupHasBall)
                        startMix()
                    } else showResult(result)
                }
                start()
            }
        })
    }

    private fun startMix() {
        setFirst()
        setSecond()
        setThird()
    }

    private fun setFirst() {
        ObjectAnimator.ofFloat(binding.flFirst, "translationX", (500..700).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.flFirst, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.flFirst,
                        "translationX",
                        (500..700).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.flFirst, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.flFirst,
                                "translationX",
                                (500..700).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.flFirst, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.flFirst,
                                        "translationX",
                                        (500..700).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.flFirst, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.flFirst,
                                                    "translationX",
                                                    (500..700).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.flFirst,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.flFirst,
                                                            "translationX",
                                                            (500..700).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.flFirst, "translationX", 0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.flFirst,
                                                                    "translationX",
                                                                    (500..700).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.flFirst,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.flFirst,
                                                                            "translationX",
                                                                            (500..700).random()
                                                                                .toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.flFirst,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.ivCup1.isEnabled = true
                                                                                binding.laDownCup1.visibility = View.VISIBLE
                                                                                binding.laDownCup2.visibility = View.VISIBLE
                                                                                binding.laDownCup3.visibility = View.VISIBLE

                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }

    private fun setSecond() {
        ObjectAnimator.ofFloat(binding.flSecond, "translationX", (-500..500).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.flSecond, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.flSecond,
                        "translationX",
                        (-500..500).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.flSecond, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.flSecond,
                                "translationX",
                                (-500..500).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.flSecond, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.flSecond,
                                        "translationX",
                                        (-500..500).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.flSecond, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.flSecond,
                                                    "translationX",
                                                    (-500..500).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.flSecond,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.flSecond,
                                                            "translationX",
                                                            (-500..500).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.flSecond,
                                                                "translationX",
                                                                0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.flSecond,
                                                                    "translationX",
                                                                    (-500..500).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.flSecond,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.flSecond,
                                                                            "translationX",
                                                                            (-500..500).random()
                                                                                .toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.flSecond,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.ivCup2.isEnabled =
                                                                                    true
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }

    private fun setThird() {
        ObjectAnimator.ofFloat(binding.flThird, "translationX", (-700..-500).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.flThird, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.flThird,
                        "translationX",
                        (-700..-500).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.flThird, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.flThird,
                                "translationX",
                                (-700..-500).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.flThird, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.flThird,
                                        "translationX",
                                        (-700..-500).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.flThird, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.flThird,
                                                    "translationX",
                                                    (-700..-500).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.flThird,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.flThird,
                                                            "translationX",
                                                            (-700..-500).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.flThird,
                                                                "translationX",
                                                                0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.flThird,
                                                                    "translationX",
                                                                    (-700..-500).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.flThird,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.flThird,
                                                                            "translationX",
                                                                            (-700..-500).random()
                                                                                .toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.flThird,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.ivCup3.isEnabled =
                                                                                    true
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }
}