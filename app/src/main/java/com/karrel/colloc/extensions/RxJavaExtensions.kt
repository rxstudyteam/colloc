package karrel.com.mvvmsample.extensions

import com.karrel.colloc.extensions.FragmentDisposable
import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) = this.add(disposable)

operator fun FragmentDisposable.plusAssign(disposable: Disposable) = this.add(disposable)